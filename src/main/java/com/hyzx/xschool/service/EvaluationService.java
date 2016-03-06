package com.hyzx.xschool.service;

import org.springframework.data.domain.Page;

import com.hyzx.xschool.domain.Evaluation;
import com.hyzx.xschool.web.controller.request.EvaluationFormBean;
import com.hyzx.xschool.web.controller.request.EvaluationQueryFormBean;

/**
 * Created by jack on 15-11-8.
 */
public interface EvaluationService {
  void save(EvaluationFormBean evaluation);
  
  /**
   * 分页
   * 
   * @param eq
   * @return
   */
  Page<Evaluation> findByPage(EvaluationQueryFormBean eq);

  void remove(Long id);

  void modifyStatus(Long id, int status);
}
