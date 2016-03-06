package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 15-11-8.
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	Page<Evaluation> findByNameContaining(String name, Pageable pageable);

	Evaluation findByName(String name);
}
