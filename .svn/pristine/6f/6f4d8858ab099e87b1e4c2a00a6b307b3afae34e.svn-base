package com.hyzx.xschool.web.controller.evaluation;

import com.alibaba.fastjson.JSONObject;
import com.hyzx.xschool.domain.Topic;
import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.TopicRepository;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.TopicService;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.TopicFormBean;
import com.hyzx.xschool.web.controller.request.TopicOption;
import com.hyzx.xschool.web.controller.request.TopicQueryBean;
import com.hyzx.xschool.web.controller.response.TopicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * 测评管理->题目管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "测评管理->题目管理", produces = "application/json")
@RestController
@RequestMapping("/evaluation/topic")
public class TopicController {

	private static Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

	@Autowired
	TopicRepository topicRepository;
	@Autowired
	TopicService topicService;
	@Autowired
	TopicStoreRepository topicStoreRepository;

	/**
   * 查看题目列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "题目管理：题目列表", notes = "分页获取题目数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<Topic> list(@RequestBody TopicQueryBean q) {
    LOGGER.info("查看题目 -> {}", q.toString());
    LOGGER.info(q.getTopicStoreId()+"sdfs");
   /* Pageable pageable =
        new PageRequest(q.getPageNo() - 1 , q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
    Page<Topic> page =
        topicRepository.findByNameContainingAndTopicStoreId(q.getName(), q.getTopicStoreId(), pageable);*/
    Page<Topic>page=topicService.findByNameAndTopicStoreId(q);
    if (!page.hasContent()) {
      return RestResult.ok(Collections.emptyList());
    }

    List<Long> topicStoreIds =
        page.getContent().stream().map(Topic::getTopicStoreId).collect(toList());
    List<TopicStore> topicStores = topicStoreRepository.findAll(topicStoreIds);
    Map<Long, String> topicStoreMap =
        topicStores.stream().collect(toMap(TopicStore::getId, TopicStore::getName));

    // 构造页面展示数据
    List<TopicInfo> list = page.getContent().stream().map(c -> {
      TopicInfo info = new TopicInfo();
      info.setId(c.getId());
      info.setName(c.getName());
      info.setType(c.getType());
      info.setTpicStoreName(topicStoreMap.get(c.getTopicStoreId()));
      return info;
    }).collect(toList());
    return RestResult.okPage(list, (int) page.getTotalElements());
  }

	/**
	 * 编辑题目.
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "题目管理-题目列表：编辑", notes = "获取待编辑的题目信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<TopicFormBean> detail(@PathVariable("id") Long id) {
		Topic t = topicRepository.findOne(id);
		if (t == null) {
			throw new BizException("题目不存在");
		}

		TopicFormBean topic = new TopicFormBean();
		topic.setId(t.getId());
		topic.setName(t.getName());
		topic.setTopicStoreId(t.getTopicStoreId());
		topic.setType(t.getType().intValue());
		topic.setOptions(JSONObject.parseArray(t.getOptions(), TopicOption.class));
		return RestResult.ok(topic);
	}

	/**
	 * 保存题目.
	 *
	 * @param topic
	 * @return
	 */
	@ApiOperation(value = "题目管理-题目列表：编辑->保存", notes = "保存题目信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RestResult<Void> save(@Valid @RequestBody TopicFormBean topic) {
		LOGGER.info("保存题目 -> {}", topic.toString());
    topicService.save(topic);
    return RestResult.ok();
  }

	/**
	 * 删除题目.
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "题目管理-题目列表：删除", notes = "删除题目记录")
	@RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
	public RestResult<Void> remove(@PathVariable("id") Long id) {
		Topic t = topicRepository.findOne(id);
		
		topicRepository.delete(id);
		Integer topicNum = topicRepository.countByTopicStoreId(t.getTopicStoreId());
		TopicStore ts = topicStoreRepository.findOne(t.getTopicStoreId());
		ts.setTopicNum(topicNum);
		topicStoreRepository.save(ts);
		return RestResult.ok();
	}

}
