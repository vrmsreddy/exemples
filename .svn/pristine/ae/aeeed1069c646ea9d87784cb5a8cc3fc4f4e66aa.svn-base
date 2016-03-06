package com.hyzx.xschool.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyzx.xschool.domain.Task;

/**
 * Created by jack on 15-11-8.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

  Page<Task> findByNameAndTopicStoreId(String name, Long topicStoreId, Pageable pageable);
  
  Page<Task> findByNameContainingAndTopicStoreId(String name, Long topicStoreId, Pageable pageable);
  
  Page<Task>findByNameContaining(String name,Pageable pageable);
  
  Page<Task>findByTopicStoreId(Long topicStoreId,Pageable pageable);

  Integer countByTopicStoreId(Long topicStoreId);

  Task findFirstByTopicStoreId(Long topicStoreId);
}
