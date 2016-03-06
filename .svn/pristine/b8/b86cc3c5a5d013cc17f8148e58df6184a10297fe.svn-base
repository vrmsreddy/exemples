package com.hyzx.xschool.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.web.controller.request.TopicStoreQueryBean;
import com.hyzx.xschool.web.controller.response.SelectedTopicStore;
/**
 * 题库service
 * @author qly
 *
 */
public interface TopicStoreService {

	  /**
	   * 分页数据
	   * @param ts
	   * @return
	   */
	  Page<TopicStore> findByPage(TopicStoreQueryBean ts);
	  /**
	   * 删除
	   * @param id
	   */
	  void deleteTopicStoreById(Long id);
	  /**
	   * 所有数据
	   * @return
	   */
	  List<SelectedTopicStore>selectTopicStoreList();
}
