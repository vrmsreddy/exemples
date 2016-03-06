package com.hyzx.xschool.web.controller.organization;

import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.service.CommentService;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.CommentQueryFormBean;
import com.hyzx.xschool.web.controller.response.CommentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机构管理-评论管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "机构管理->评论管理", produces = "application/json")
@RestController
@RequestMapping("/organization/comment")
public class CommentController {

  private static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  CommentService commentService;

  /**
   * 查看评论列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "评论管理：评论列表", notes = "分页获取评论数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<CommentInfo> list(@RequestBody CommentQueryFormBean q) {
    LOGGER.info("查看评论 -> {}", q.toString());
    Page<CommentInfo> page = commentService.findByPage(q);
    return RestResult.ok(page);
  }

  /**
   * 更新状态：启用/禁用.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "机构管理-评论列表：审核/删除操作", notes = "更新评论状态，当status为-1时表示删除")
  @RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id,
    @PathVariable("status") int status) {
    commentService.updateStatus(id,status);
    return RestResult.ok();
  }

}
