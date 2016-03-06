package com.hyzx.xschool.service;

import com.hyzx.xschool.web.controller.request.TopicFormBean;
import org.springframework.data.domain.Page;

import com.hyzx.xschool.domain.Topic;
import com.hyzx.xschool.web.controller.request.TopicQueryBean;

/**
 * 题目service
 * @author qly
 *
 */
public interface TopicService {

	/**
	 * 
	 * @param topic
	 * @return
	 */
	Page<Topic>findByNameAndTopicStoreId(TopicQueryBean topic);

	void save(TopicFormBean topic);
}
