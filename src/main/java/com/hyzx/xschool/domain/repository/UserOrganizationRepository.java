package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-11-26 13:45
 */
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {
  List<UserOrganization> findByUserId(Long userId);
}
