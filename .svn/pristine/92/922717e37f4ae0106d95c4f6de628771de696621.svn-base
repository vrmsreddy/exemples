package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.domain.Feedback;
import com.hyzx.xschool.domain.repository.FeedbackRepository;
import com.hyzx.xschool.domain.repository.UserRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.FeedbackService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.FeedbackQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 反馈管理-反馈列表
 *
 * @author caoxudong
 * @since 0.1.10
 */
@Api(value = "系统管理->反馈管理", produces = "application/json")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

  private static Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

  @Autowired
  FeedbackRepository feedbackRepository;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  FeedbackService feedbackService;
  /**
   * 查看反馈列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "反馈列表", notes = "分页获取反馈数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<Feedback> list(@RequestBody FeedbackQueryVo q) {
    LOGGER.info("查看反馈 -> {}", q.toString());

    Page<Feedback> page = feedbackService.findByPage(q);
    if (!page.hasContent()) {
      return RestResult.okPage(Collections.emptyList(), 0);
    }
    page.getContent().forEach(f->{
      f.setCreateTimeStr(new DateTime(Long.parseLong(f.getCreateTime())).toString(Constants.DATE_TIME_LONG_FORMAT));
    });
    return RestResult.ok(page);
  }

  /**
   * 更新处理状态.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "反馈管理-反馈列表：处理操作", notes = "更新反馈状态")
  @RequestMapping(value = "/{id}/handle", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id) {

    Feedback feedback = feedbackRepository.findOne(id);
    if (feedback == null) {
      throw new BizException("反馈不存在");
    }

    // 设置为“已处理”
    feedback.setStatus(2);
    feedbackRepository.save(feedback);
    return RestResult.ok();
  }
}
