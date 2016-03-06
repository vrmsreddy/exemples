package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.UserTrainPlanRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 15-11-1.
 */
public interface UserTrainPlanRecordRepository extends JpaRepository<UserTrainPlanRecord, Long> {

  UserTrainPlanRecord findFirstByEvaluationId(Long evaluationId);
}
