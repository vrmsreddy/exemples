package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 反馈dao
 * @author qly
 *
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

// @Query("select f from Feedback f left join f.userId as user")
  Page<Feedback> findByStatusAndContactContaining(Integer status, String contact, Pageable pageable);
  
  Page<Feedback> findByStatus(Integer status, Pageable pageable);
  
  Page<Feedback>findByContactContaining(String contact,Pageable pageable);
}
 