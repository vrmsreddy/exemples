package com.hyzx.xschool.web.controller.evaluation;

import com.hyzx.xschool.domain.Evaluation;
import com.hyzx.xschool.domain.EvaluationTopicStore;
import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.EvaluationRepository;
import com.hyzx.xschool.domain.repository.EvaluationTopicStoreRepository;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.EvaluationService;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.EvaluationFormBean;
import com.hyzx.xschool.web.controller.request.EvaluationQueryFormBean;
import com.hyzx.xschool.web.controller.response.EvaluationInfo;
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
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 测评管理-测评管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "测评管理", produces = "application/json")
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

  private static Logger LOGGER = LoggerFactory.getLogger(EvaluationController.class);
  @Autowired
  TopicStoreRepository topicStoreRepository;
  @Autowired
  EvaluationRepository evaluationRepository;
  @Autowired
  EvaluationService evaluationService;
  @Autowired
  EvaluationTopicStoreRepository evaluationTopicStoreRepository;

  /**
   * 查看测评列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "测评管理：测评列表", notes = "分页获取测评数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<EvaluationInfo> list(@RequestBody EvaluationQueryFormBean q) {
    LOGGER.info("查看测评 -> {}", q.toString());

    // 分页取测评列表
  //  Pageable pageable =
     //   new PageRequest(q.getPageNo() - 1 , q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
   // Page<Evaluation> page = evaluationRepository.findByName(q.getName(), pageable);
    Page<Evaluation> page=evaluationService.findByPage(q);
    if (!page.hasContent()) {
      return RestResult.ok(Collections.emptyList());
    }

    // 构造页面展示数据
    List<EvaluationInfo> list = page.getContent().stream().map(e -> {
      EvaluationInfo info = new EvaluationInfo();
      info.setId(e.getId());
      info.setDetail(e.getDetail());
      info.setName(e.getName());
      info.setTopicNum(e.getTopicNum().intValue());
      info.setClickNum(e.getClickNum().intValue());
      info.setStatus(e.getStatus().toString());
      return info;
    }).collect(toList());
    return RestResult.okPage(list, (int) page.getTotalElements());
  }

  /**
   * 获取待编辑的测评及其下的题库.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "测评管理-编辑测评", notes = "获取待编辑的测评及题库列表")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<EvaluationFormBean> detail(@PathVariable("id") Long id) {
    Evaluation evaluation = evaluationRepository.findOne(id);
    if (evaluation == null) {
      throw new BizException("测评不存在");
    }
    List<EvaluationTopicStore> topicStores = evaluationTopicStoreRepository.findByEvaluationId(id);

    EvaluationFormBean vo = new EvaluationFormBean();
    vo.setId(evaluation.getId());
    vo.setName(evaluation.getName());
    vo.setDetail(evaluation.getDetail());
    vo.setTopicStoreList(topicStores);
    return RestResult.ok(vo);
  }

  /**
   * 保存评测.
   *
   * @param evaluation
   * @return
   */
  @ApiOperation(value = "评测管理-评测列表：编辑->保存", notes = "保存评测信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@Valid @RequestBody EvaluationFormBean evaluation) {
    LOGGER.info("保存评测 -> {}", evaluation.toString());
    List<EvaluationTopicStore> list = evaluation.getTopicStoreList();
    for (EvaluationTopicStore ts : list) {
      TopicStore store = topicStoreRepository.findOne(ts.getTopicStoreId());
      Integer topicNum = store.getTopicNum();

      Validate.isTrue(ts.getTopicNum() <= topicNum, "评测题数不能超过［" + store.getName() + "］题库的总题数");

    }
    evaluationService.save(evaluation);
    return RestResult.ok();
  }

  /**
   * 更新状态：启用/禁用.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "测评管理-测评列表：开启/禁用、删除操作", notes = "更新测评状态，当status为-1时表示删除")
  @RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id,
    @PathVariable("status") int status) {
    evaluationService.modifyStatus(id, status);
    return RestResult.ok();
  }

  /**
   * 删除评测.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "评测管理-评测列表：删除", notes = "删除评测记录")
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
  public RestResult<Void> remove(@PathVariable("id") Long id) {
    evaluationService.remove(id);
    return RestResult.ok();
  }
}
