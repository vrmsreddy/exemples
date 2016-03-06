package com.hyzx.xschool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.hyzx.xschool.domain.Topic;
import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.TopicRepository;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.TopicService;
import com.hyzx.xschool.web.controller.request.TopicFormBean;
import com.hyzx.xschool.web.controller.request.TopicQueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * topicService实现类
 * 
 * @author qly
 *
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicStoreRepository topicStoreRepository;

	@Override
	public Page<Topic> findByNameAndTopicStoreId(TopicQueryBean t) {
		Pageable pageable = new PageRequest(t.getPageNo() - 1, t.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

		Page<Topic> page = null;
		if (Strings.isNullOrEmpty(t.getName()) && t.getTopicStoreId()<1) {
			page = topicRepository.findAll(pageable);
		} else if (Strings.isNullOrEmpty(t.getName())) {
			page = topicRepository.findByTopicStoreId(t.getTopicStoreId(), pageable);
		} else if (t.getTopicStoreId()<1) {
			page = topicRepository.findByNameContaining(t.getName(), pageable);
		} else {
			page = topicRepository.findByNameContainingAndTopicStoreId(t.getName(), t.getTopicStoreId(), pageable);
		}
		return page;
	}

	@Override
	public void save(TopicFormBean topic) {
		Topic topicToSave = null;
	  // 修改时-旧的题库主键
    Long oldTopicStoreId = null;
		if (topic.getId() != null) {
			topicToSave = topicRepository.findOne(topic.getId());
			if (topicToSave == null) {
				throw new BizException("题目不存在");
			}
			oldTopicStoreId = topicToSave.getTopicStoreId();
		} else {
			topicToSave = new Topic();
		}

		topicToSave.setName(topic.getName());
		topicToSave.setTopicStoreId(topic.getTopicStoreId());
		topicToSave.setType(topic.getType());
		String options = JSONObject.toJSONString(topic.getOptions());
		topicToSave.setOptions(options);
		topicRepository.save(topicToSave);

		// 更新对应题库中的题目数量
		Integer topicNum = topicRepository.countByTopicStoreId(topicToSave.getTopicStoreId());
		TopicStore ts = topicStoreRepository.findOne(topicToSave.getTopicStoreId());
		ts.setTopicNum(topicNum);
		topicStoreRepository.save(ts);
		
	  // 更新题目时，若修改了所在题库，则更新下旧的题库题目数量
    if (oldTopicStoreId != null && oldTopicStoreId.longValue() != topicToSave.getTopicStoreId().longValue()) {
      Integer oldTopicNum = topicRepository.countByTopicStoreId(oldTopicStoreId);
      TopicStore oldTs = topicStoreRepository.findOne(oldTopicStoreId);
      oldTs.setTopicNum(oldTopicNum);
      topicStoreRepository.save(oldTs);
    }
	}

}
