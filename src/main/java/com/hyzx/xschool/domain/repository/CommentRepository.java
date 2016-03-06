package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 15-11-7.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Page<Comment> findByStatusAndCreateTimeBetween(Integer status, String startTime,
      String endTime, Pageable pageable);
}
