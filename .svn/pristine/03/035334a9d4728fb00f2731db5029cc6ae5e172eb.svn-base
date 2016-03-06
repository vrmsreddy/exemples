package com.hyzx.xschool.web.controller.organization;

import com.google.common.base.Strings;
import com.hyzx.xschool.domain.Location;
import com.hyzx.xschool.domain.Region;
import com.hyzx.xschool.domain.enums.LocationStatus;
import com.hyzx.xschool.domain.repository.LocationRepository;
import com.hyzx.xschool.domain.repository.RegionRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.LocationService;
import com.hyzx.xschool.util.ValidateUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.LocationQueryFormBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * 机构管理-位置管理
 *
 * @author caoxudong
 * @since 0.1.10
 */
@Api(value = "机构管理->位置管理", produces = "application/json")
@RestController
@RequestMapping("/organization/location")
public class LocationController {

  private static Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

  @Autowired
  LocationRepository locationRepository;

  @Autowired
  RegionRepository regionRepository;
  @Autowired
  private LocationService locationService;

  /**
   * 获取某个行政区域的下一级子区域
   *
   * @param parentId
   * @return
   */
  @ApiOperation(value = "位置管理：获取行政区列表", notes = "根据父区域ID获取下一级子行政区域列表，中国的id为1")
  @RequestMapping(value = "/areas/{parentId}", method = RequestMethod.GET)
  public RestResult<Region> listChildRegion(@PathVariable("parentId") Long parentId) {
    LOGGER.info("获取城市的下属行政区-{}", parentId);

    List<Region> list = regionRepository.findByParentId(parentId);
    return RestResult.ok(list);
  }

  /**
   * 查看位置列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "位置管理：位置列表", notes = "分页获取位置数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<Location> list(@RequestBody LocationQueryFormBean q) {
    LOGGER.info("查看位置 -> {}", q.toString());

    int index = q.getPageNo() - 1;
    Pageable pageable = new PageRequest(index, q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
    Page<Location> page = null;
    if (Strings.isNullOrEmpty(q.getName())) {
      page = locationRepository.findAll(pageable);
    } else {
      page = locationRepository.findByName(q.getName(), pageable);
    }
    return RestResult.ok(page);
  }

  /**
   * 编辑位置.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "位置管理-位置列表：编辑", notes = "获取待编辑的位置信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<Location> detail(@PathVariable("id") Long id) {
    Location location = locationRepository.findOne(id);
    return RestResult.ok(location);
  }

  /**
   * 保存位置.
   *
   * @param location
   * @return
   */
  @ApiOperation(value = "位置管理-位置列表：编辑->保存", notes = "保存位置信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@Valid @RequestBody Location location) {
    LOGGER.info("保存位置 -> {}", location.toString());
    Integer order =  location.getPriority();
    Validate.isTrue(null!=order,"排序值不可为空");
    Validate.isTrue(ValidateUtil.range(order,1,100), "排序值必须在1-100之间"); //organization.getHeat()

    locationService.save(location);
    return RestResult.ok();
  }

  /**
   * 删除位置.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "位置管理-位置列表：删除", notes = "删除位置记录")
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
  public RestResult<Void> remove(@PathVariable("id") Long id) {

    locationService.remove(id);
    return RestResult.ok();
  }

  /**
   * 更新状态：启用/关闭.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "位置管理-位置列表：开通/禁用", notes = "更新位置状态")
  @RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id, @PathVariable("status")
  LocationStatus status) {

    Location location = locationRepository.findOne(id);
    if (location == null) {
      throw new BizException("位置不存在");
    }

    location.setStatus(status.getCode());
    locationRepository.save(location);

    Region city = regionRepository.findOne(location.getCityId());
    // 禁用位置时，根据情况判断是否需要关闭对应的城市
    if (status == LocationStatus.DISABLE) {
      // 取出当前包含该城市的两个有效的位置
      List<Location> validLocations = locationRepository
          .findByStatusAndCityId(LocationStatus.ENABLE.getCode(), location.getCityId(),
              new PageRequest(0, 2));
      int currentValidNum = validLocations.size();
      // 当该城市下不存在开通的位置时，或者仅存在一个开通位置且该位置是当前要禁用的位置，才关闭城市的开通
      if (CollectionUtils.isEmpty(validLocations) || (currentValidNum == 1 && validLocations.get(0)
          .equals(location.getId()))) {
        city.setIsOpened(false);
        regionRepository.save(city);
      }
    }else {
      // 开通位置时，直接开通对应的城市
      city.setIsOpened(true);
      regionRepository.save(city);
    }
    return RestResult.ok();
  }

  /**
   * 获取已开通的城市.
   *
   * @return
   */
  @ApiOperation(value = "机构管理：添加机构-城市下拉框", notes = "获取已开通的城市")
  @RequestMapping(value = "/opened/city", method = RequestMethod.GET)
  public RestResult<Location> listOpenedCity() {
    List<Location> list = locationRepository.findByStatus(LocationStatus.ENABLE.getCode());
    Set<Region> citySet = list.stream().map(l -> {
      Region region = new Region();
      region.setId(l.getCityId());
      region.setName(l.getCity());
      return region;
    }).collect(toSet());

    return RestResult.ok(citySet);
  }

  @ApiOperation(value = "机构管理：添加机构-区县下拉框", notes = "获取指定城市下的区县")
  @RequestMapping(value = "/opened/county/{parentId}", method = RequestMethod.GET)
  public RestResult<Location> listOpenedCounty(@PathVariable("parentId") Long parentId) {
    List<Location> list = locationRepository.findByStatusAndCityId(LocationStatus.ENABLE.getCode(), parentId);
    Set<Region> countySet = list.stream().map(l -> {
      Region region = new Region();
      region.setId(l.getRegionId());
      region.setName(l.getCounty());
      return region;
    }).collect(toSet());

    return RestResult.ok(countySet);
  }

  @ApiOperation(value = "机构管理：添加机构-位置下拉框", notes = "获取指定区县下的已开通的位置")
  @RequestMapping(value = "/opened/{countyId}/list", method = RequestMethod.GET)
  public RestResult<Location> listOpened(@PathVariable("countyId") Long countyId) {
    List<Location> list = locationRepository.findByStatusAndRegionId(LocationStatus.ENABLE.getCode(), countyId);
    return RestResult.ok(list);
  }


}
