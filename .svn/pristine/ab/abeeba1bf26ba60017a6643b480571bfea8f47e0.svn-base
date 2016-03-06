package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-7.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
	Page<Course> findByName(String name, Pageable pageable);

	Page<Course> findByNameContaining(String name, Pageable pageable);

	List<Course> findByOrganizationId(Long organId);

	Course findFirstByCategoryId(Long id);


	Course findByNameAndOrganizationId(String name, long organizationId);
}
