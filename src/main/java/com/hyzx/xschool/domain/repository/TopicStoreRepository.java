package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.TopicStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 15-11-1.
 */
public interface TopicStoreRepository extends JpaRepository<TopicStore, Long> {
	
  Page<TopicStore> findByNameContaining(String name, Pageable pageable);
  TopicStore findByName(String name);
}
