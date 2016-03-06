package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.domain.Article;
import com.hyzx.xschool.domain.Resource;
import com.hyzx.xschool.domain.repository.ArticleRepository;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.domain.repository.ResourceRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.ArticleService;
import com.hyzx.xschool.util.ValidateUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.ArticleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 文章管理-文章列表
 *
 * @author caoxudong
 * @since 0.1.10
 */
@Api(value = "系统管理->文章管理", produces = "application/json")
@RestController
@RequestMapping("/article")
public class ArticleController {

	private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	ArticleService articleService;

	@Autowired
	ResourceRepository resourceRepository;

	/**
	 * 查看文章列表.
	 *
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "文章列表", notes = "分页获取文章数据，带搜索条件")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RestResult<Article> list(@RequestBody ArticleQueryVo q) {
		LOGGER.info("查看文章 -> {}", q.toString());
		Page<Article> page = articleService.findByPage(q);
		return RestResult.ok(page);
	}

	/**
	 * 编辑文章.
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "文章列表：查看基本信息", notes = "查看文章信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<Article> detail(@PathVariable("id") Long id) {
		Article article = articleRepository.findOne(id);
		if (article == null) {
			throw new BizException("文章不存在");
		}

		Resource resource = resourceRepository.findOne(article.getResourceId());
    if (resource != null) {
      article.setPicPath(resource.getPath());
    }
    return RestResult.ok(article);
	}

	/**
	 * 保存文章信息.
	 *
	 * @param article
	 * @return
	 */
	@ApiOperation(value = "文章列表：基本信息->保存", notes = "保存文章信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RestResult<Void> save(@Valid @RequestBody Article article) {
		LOGGER.info("保存文章 -> {}", article.toString());

		Integer order = article.getPriority();
		Validate.isTrue(null != order, "排序值不可为空");
		Validate.isTrue(ValidateUtil.range(order, 1, 999), "排序值必须在1- 999之间");

		Integer type = article.getType();
		if (type == 2) {
			Validate.isTrue(null != article.getOrganizationId(), "类型为机构时，机构字段必选");
		}
		if (type == 3) {
			Validate.isTrue(null != article.getUrl(), "类型为第三方页面时，url字段必填");
		}

		articleService.save(article);
		return RestResult.ok();
	}

	/**
	 * 更新状态：启用/禁用.
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "文章管理-文章列表：启用/禁用", notes = "更新文章状态")
	@RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
	public RestResult<Void> updateStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {

		Article article = articleRepository.findOne(id);
		if (article == null) {
			throw new BizException("文章不存在");
		}

		article.setStatus(status);
		articleRepository.save(article);
		return RestResult.ok();
	}
}
