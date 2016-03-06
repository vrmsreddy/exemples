package com.hyzx.xschool.service.impl;

import com.hyzx.xschool.domain.Location;
import com.hyzx.xschool.domain.Organization;
import com.hyzx.xschool.domain.enums.OpenStatus;
import com.hyzx.xschool.domain.repository.LocationRepository;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.LocationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-11-25 22:44
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  OrganizationRepository organizationRepository;

  @Override
  public void remove(Long id) {
    Organization organ = organizationRepository.findFirstByLocationId(id);
    if (organ != null) {
      throw new BizException("有机构已添加了该位置，不允许删除");
    }
    locationRepository.delete(id);
  }

  @Override
  public void save(Location location) {
    Date now = DateTime.now().toDate();
    Location locationToSave = null;
    if (location.getId() != null) {
      locationToSave = locationRepository.findOne(location.getId());
      if (locationToSave == null) {
        throw new BizException("位置不存在");
      }
    } else {
      locationToSave = new Location();
      // 默认为“已禁止”状态
      locationToSave.setStatus(OpenStatus.INVALID.getCode());
      locationToSave.setCreateTime(now);
    }

    locationToSave.setName(location.getName());
    locationToSave.setProvinceId(location.getProvinceId());
    locationToSave.setCityId(location.getCityId());
    locationToSave.setRegionId(location.getRegionId());
    locationToSave.setPriority(location.getPriority());
    // 设置冗余字段
    locationToSave.setCity(location.getCity());
    locationToSave.setCounty(location.getCounty());

    locationToSave.setModifyTime(now);
    locationRepository.save(locationToSave);
  }
}
