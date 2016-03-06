package com.hyzx.xschool.web.controller.organization;

import com.hyzx.xschool.domain.Category;
import com.hyzx.xschool.domain.repository.CategoryRepository;
import com.hyzx.xschool.domain.repository.ResourceRepository;
import com.hyzx.xschool.service.CategoryService;
import com.hyzx.xschool.util.ValidateUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.CategoryFormBean;
import com.hyzx.xschool.web.controller.request.CategoryQueryFormBean;
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
import java.util.List;

/**
 * 机构管理-课程分类管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "机构管理->课程分类管理", produces = "application/json")
@RestController
@RequestMapping("/organization/category")
public class CategoryController {

  private static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  ResourceRepository resourceRepository;
  
  @Autowired
  CategoryService categoryService;
  /**
   * 获取父分类列表.
   *
   * @return
   */
  @ApiOperation(value = "课程分类管理：父级分类下拉框", notes = "获取父级课程分类列表")
  @RequestMapping(value = "/parent/list", method = RequestMethod.GET)
  public RestResult<Category> list() {
    List<Category> list = categoryRepository.findByParentCategoryIdIsNull();
    return RestResult.ok(list);
  }

  /**
   * 课程分类管理：分页查看分类列表.
   * 课程管理：编辑课程时根据父类目获取子类目
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "课程分类管理：课程分类列表", notes = "获取课程分类数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<Category> list(@Valid @RequestBody CategoryQueryFormBean q) {
    LOGGER.info("查看课程分类 -> {}", q.toString());

    Page<Category> page = categoryService.finzdByPage(q);
    return RestResult.ok(page);
  }

  /**
   * 添加课程分类.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "课程分类管理-课程分类列表：编辑", notes = "获取待编辑的课程分类信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<Category> detail(@PathVariable("id") Long id) {
    Category Category = categoryRepository.findOne(id);
    return RestResult.ok(Category);
  }

  /**
   * 保存课程分类.
   *
   * @param category
   * @return
   */
  @ApiOperation(value = "课程分类管理-课程分类列表：编辑->保存", notes = "保存课程分类信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@RequestBody CategoryFormBean category) {
    LOGGER.info("保存课程分类 -> {}", category.toString());
    //排序：必填，限制范围1-100，默认值50
   int order =  category.getOrder();
   Validate.isTrue(ValidateUtil.range(order,1,100), "排序值必须在1-100之间"); //organization.getHeat()

    categoryService.save(category);
    return RestResult.ok();
  }

  /**
   * 删除课程分类.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "课程分类管理-课程分类列表：删除", notes = "删除课程分类记录")
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
  public RestResult<Integer> remove(@PathVariable("id") Long id) {
    categoryService.remove(id);
    return RestResult.ok();
  }
}
