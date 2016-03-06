package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.OrganizationResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-7.
 */
public interface OrganizationResourceRepository extends JpaRepository<OrganizationResource, Long> {

  List<OrganizationResource> findByOrganizationId(Long id);

  void deleteByResourceId(Long resourceId);

}
