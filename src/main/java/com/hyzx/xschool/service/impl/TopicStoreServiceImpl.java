package com.hyzx.xschool.service.impl;

import com.google.common.base.Strings;
import com.hyzx.xschool.domain.EvaluationTopicStore;
import com.hyzx.xschool.domain.Task;
import com.hyzx.xschool.domain.Topic;
import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.EvaluationTopicStoreRepository;
import com.hyzx.xschool.domain.repository.TaskRepository;
import com.hyzx.xschool.domain.repository.TopicRepository;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.TopicStoreService;
import com.hyzx.xschool.web.controller.request.TopicStoreQueryBean;
import com.hyzx.xschool.web.controller.response.SelectedTopicStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 题库service实现类
 * 
 * @author qly
 *
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class TopicStoreServiceImpl implements TopicStoreService {

	@Autowired
	TopicStoreRepository topicStoreRepository;
  @Autowired
  private TopicRepository topicRepository;
  @Autowired
  private EvaluationTopicStoreRepository evaluationTopicStoreRepository;
  @Autowired
  private TaskRepository taskRepository;

  @Override
	public Page<TopicStore> findByPage(TopicStoreQueryBean ts) {
		Pageable pageable = new PageRequest(ts.getPageNo() - 1, ts.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

		Page<TopicStore> page = null;
		if (Strings.isNullOrEmpty(ts.getName())) {
			page = topicStoreRepository.findAll(pageable);
		} else {
			page = topicStoreRepository.findByNameContaining(ts.getName(), pageable);
		}

		return page;
	}

	@Override
	public void deleteTopicStoreById(Long id) {
    EvaluationTopicStore ets = evaluationTopicStoreRepository.findFirstByTopicStoreId(id);
    if (ets != null) {
      throw new BizException("该题库已被测评使用，不允许删除");
    }
    Task task = taskRepository.findFirstByTopicStoreId(id);
    if (task != null) {
      throw new BizException("该题库已被任务使用，不允许删除");
    }
    Topic topic = topicRepository.findFirstByTopicStoreId(id);
    if (topic != null) {
      throw new BizException("该题库中包含题目，不允许删除");
    }

    topicStoreRepository.delete(id);
  }

	@Override
	public List<SelectedTopicStore> selectTopicStoreList() {
		List<TopicStore>list= topicStoreRepository.findAll();
		List<SelectedTopicStore> alist=new ArrayList<SelectedTopicStore>();
		if(list!=null&&list.size()>0){
			for(TopicStore p:list){
				SelectedTopicStore  st=new SelectedTopicStore();
				st.setId(p.getId());
				st.setName(p.getName());
				alist.add(st);
			}
		}
		return alist;
	}
}
