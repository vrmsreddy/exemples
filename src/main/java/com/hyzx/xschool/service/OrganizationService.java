package com.hyzx.xschool.service;

import com.hyzx.xschool.domain.Organization;
import com.hyzx.xschool.domain.OrganizationResource;
import com.hyzx.xschool.domain.enums.OpenStatus;
import com.hyzx.xschool.web.controller.request.OrgQueryFormBean;
import com.hyzx.xschool.web.controller.response.OrganizationInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jack on 15-11-7.
 */
public interface OrganizationService {

  void saveOrganPicRescources(List<OrganizationResource> picList);

  Organization saveOrgan(Organization organization);

  Page<OrganizationInfo> findByPage(OrgQueryFormBean q);

  void updateStatus(Long id, OpenStatus status, String name);
}
