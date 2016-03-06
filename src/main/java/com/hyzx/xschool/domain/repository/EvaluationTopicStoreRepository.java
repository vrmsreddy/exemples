package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.EvaluationTopicStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-8.
 */
public interface EvaluationTopicStoreRepository extends JpaRepository<EvaluationTopicStore, Long> {
  List<EvaluationTopicStore> findByEvaluationId(Long id);
  void deleteByEvaluationId(Long evaluationId);

  EvaluationTopicStore findFirstByTopicStoreId(Long topicStoreId);
}
