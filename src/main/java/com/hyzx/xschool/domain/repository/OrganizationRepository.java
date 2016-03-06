package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-2.
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

  Page<Organization> findByNameContainingAndPhone(String name, String phone, Pageable pageable);
  Page<Organization> findByNameContaining(String name, Pageable pageable);
  Page<Organization> findByPhone(String phone, Pageable pageable);

  List<Organization> findByStatus(Integer code);

  Organization findFirstByLocationId(Long locationId);
}
