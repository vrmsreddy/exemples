package com.hyzx.xschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.hyzx.xschool.domain.Feedback;

import com.hyzx.xschool.domain.repository.FeedbackRepository;
import com.hyzx.xschool.service.FeedbackService;
import com.hyzx.xschool.web.controller.request.FeedbackQueryVo;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository FeedbackRepository;

	@Override
	public Page<Feedback> findByPage(FeedbackQueryVo fd) {
		Pageable pageable = new PageRequest(fd.getPageNo() - 1, fd.getPageSize(),
				new Sort(Sort.Direction.DESC, "id"));

		Page<Feedback> page = null;
		if (Strings.isNullOrEmpty(fd.getMobile())&&fd.getStatus()<1) {
			page = FeedbackRepository.findAll(pageable);
		}else if(Strings.isNullOrEmpty(fd.getMobile())){
			page=FeedbackRepository.findByStatus(fd.getStatus(), pageable);
		}else if(fd.getStatus()<1){
			page=FeedbackRepository.findByContactContaining(fd.getMobile(), pageable);
		}else{
			page=FeedbackRepository.findByStatusAndContactContaining(fd.getStatus(), fd.getMobile(), pageable);
		}
		return page;
	}


}
