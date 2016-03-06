package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 15-11-8.
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
  Page<Topic> findByNameContainingAndTopicStoreId(String name, Long topicStoreId, Pageable pageable);
  
  Page<Topic>findByNameContaining(String name,Pageable pageable);
  
  Page<Topic>findByTopicStoreId(Long topicStoreId,Pageable pageable);

  Integer countByTopicStoreId(Long topicStoreId);

  Topic findFirstByTopicStoreId(Long topicStoreId);
}
