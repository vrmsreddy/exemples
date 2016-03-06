package com.hyzx.xschool.web.controller.organization;

import com.hyzx.xschool.domain.Organization;
import com.hyzx.xschool.domain.OrganizationResource;
import com.hyzx.xschool.domain.Resource;
import com.hyzx.xschool.domain.enums.OpenStatus;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.domain.repository.OrganizationResourceRepository;
import com.hyzx.xschool.domain.repository.ResourceRepository;
import com.hyzx.xschool.service.OrganizationService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.util.ValidateUtil;
import com.hyzx.xschool.util.session.SessionUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.OrgQueryFormBean;
import com.hyzx.xschool.web.controller.response.OrgPicInfo;
import com.hyzx.xschool.web.controller.response.OrganizationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 机构管理-机构列表
 *
 * @author caoxudong
 * @since 0.1.0
 */
@Api(value = "机构管理", produces = "application/json")
@RestController
@RequestMapping("/organization")
public class OrganController {

  private static Logger LOGGER = LoggerFactory.getLogger(OrganController.class);

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  OrganizationService organizationService;

  @Autowired
  OrganizationResourceRepository organizationResourceRepository;

  @Autowired
  ResourceRepository resourceRepository;

  /**
   * 查看机构列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "机构列表", notes = "分页获取机构数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<OrganizationInfo> list(@RequestBody OrgQueryFormBean q) {
    LOGGER.info("查看机构 -> {}", q.toString());

    Page<OrganizationInfo> page = organizationService.findByPage(q);
    return RestResult.ok(page);
  }

  /**
   * 编辑机构.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "机构列表：查看基本信息", notes = "查看机构信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<Organization> detail(@PathVariable("id") Long id) {
    Organization organizationInfo = organizationRepository.findOne(id);
    return RestResult.ok(organizationInfo);
  }

  /**
   * 保存机构信息.
   *
   * @param organization
   * @return
   */
  @ApiOperation(value = "机构列表：基本信息->保存", notes = "保存机构信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@RequestBody Organization organization,HttpServletRequest request) {
    LOGGER.info("保存机构 -> {}", organization.toString());
    //ValidateUtil.validate(result);
    Integer heat = organization.getHeat();
    Validate.isTrue(null!=heat,"热度不可为空");
    Validate.isTrue(ValidateUtil.range(heat,1,999), "热度必须在1-999之间"); //organization.getHeat()
    if(StringUtils.isNotEmpty(organization.getLongitude())){

    Validate.isTrue(ValidateUtil.checkNumber(organization.getLongitude()),"经度必须填写数字");
    }
    if(StringUtils.isNotEmpty(organization.getLatitude())){
        Validate.isTrue(ValidateUtil.checkNumber(organization.getLatitude()),"纬度必须填写数字");

    }

    String phone = organization.getPhone();
    if(StringUtils.isNotEmpty(phone)){
    Validate.isTrue( ValidateUtil.checkPhone(phone),"电话号码格式有误");
    }
    Long shareNum  = organization.getShareNum();
    Validate.isTrue(null!=shareNum,"分享数不可为空");
    String name = (String)SessionUtil.getSession(request).getAttribute(Constants.SESSION_USER_NICKNANE);
    organization.setModifier(name);
    return RestResult.ok(organizationService.saveOrgan(organization));
  }


  /**
   * 更新状态：启用/禁用.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "机构管理-机构列表：启用/禁用", notes = "更新机构状态")
  @RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id, @PathVariable("status")
  OpenStatus status,HttpServletRequest request) {
	   String name = (String)SessionUtil.getSession(request).getAttribute(Constants.SESSION_USER_NICKNANE);
	    
    organizationService.updateStatus(id,status,name);
    return RestResult.ok();
  }

  /**
   * 获取某个机构下的图片列表.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "机构管理-机构列表：图片", notes = "查看该机构的图片资源")
  @RequestMapping(value = "/{id}/pic/list", method = RequestMethod.GET)
  public RestResult<OrgPicInfo> listPictures(@PathVariable("id") Long id) {

    // 获取该机构下的资源
    List<OrganizationResource> orgResources =
        organizationResourceRepository.findByOrganizationId(id);
    if (CollectionUtils.isEmpty(orgResources)) {
      return RestResult.ok(Collections.emptyList());
    }

    // 根据资源ID批量获取图片信息
    List<Long> picIds =
        orgResources.stream().map(OrganizationResource::getResourceId).collect(toList());
    List<Resource> picList = resourceRepository.findAll(picIds);
    Map<Long, String> picPathMap =
        picList.stream().collect(Collectors.toMap(Resource::getId, Resource::getPath));

    List<OrgPicInfo> picInfoList = orgResources.stream().map(or -> {
      OrgPicInfo info = new OrgPicInfo();
      info.setId(or.getId());
      info.setResourceId(or.getResourceId());
      info.setMajor(or.isMajorPic());
      info.setOrder(or.getPriority().intValue());
      info.setPath(picPathMap.get(or.getResourceId()));
      return info;
    }).collect(toList());
    return RestResult.ok(picInfoList);
  }

  /**
   * 机构列表：图片->删除
   * @param resource  :【注意】目前只需要resourceId即可
   * @return
   */
  @ApiOperation(value = "机构列表：图片管理->删除", notes = "删除机构图片")
  @RequestMapping(value = "/pic/remove", method = RequestMethod.POST)
  public RestResult<Void> removePic(@Valid @RequestBody OrganizationResource resource) {
    LOGGER.info("remove pic resource -> {}", resource.getResourceId());

    Resource picResource = resourceRepository.findOne(resource.getResourceId());
    if (picResource == null) {
      LOGGER.error("picResource={} not exists", resource.getResourceId());
      return RestResult.ok();
    }

    // 删除机构资源记录
    organizationResourceRepository.deleteByResourceId(picResource.getId());
    // 删除资源记录
    resourceRepository.delete(picResource.getId());
    // 删除服务器上的资源文件
    if (org.apache.commons.lang.StringUtils.startsWith(picResource.getPath(), "http://")) {
      // TODO OSS删除
    } else {
      // 本地删除
      FileSystemUtils.deleteRecursively(new File(picResource.getPath()));
    }

    return RestResult.ok();
  }

  @ApiOperation(value = "机构列表：图片->保存", notes = "保存图片列表信息")
  @RequestMapping(value = "/pic/save", method = RequestMethod.POST)
  public RestResult<Void> savePicRescource(@Valid @RequestBody List<OrganizationResource> picList,HttpServletRequest request) {

    if (CollectionUtils.isEmpty(picList)) {
      return RestResult.ok();
    }
    
    organizationService.saveOrganPicRescources(picList);
    Long  oId = picList.get(0).getOrganizationId();
	
    
    String name = (String)SessionUtil.getSession(request).getAttribute(Constants.SESSION_USER_NICKNANE);
    Organization organizationInfo = organizationRepository.findOne(oId);
    Date now = DateTime.now().toDate();
    organizationInfo.setModifyTime(now);
    organizationInfo.setModifier(name);
    organizationRepository.save(organizationInfo);
    return RestResult.ok();
  }

  /**
   * 获取已开通的机构列表.
   *
   * @return
   */
  @ApiOperation(value = "课程管理-添加/编辑课程：机构列表", notes = "获取已开通的机构")
  @RequestMapping(value = "/valid/list", method = RequestMethod.GET)
  public RestResult<OrganizationInfo> list() {
    //TODO 由于此处查询了全部字段，数据量大时会有性能问题，需要优化
    List<Organization> list = organizationRepository.findByStatus(OpenStatus.VALID.getCode());
    List<OrganizationInfo> organList = list.stream().map(o -> {
      OrganizationInfo info = new OrganizationInfo();
      info.setId(o.getId());
      info.setName(o.getName());
      return info;
    }).collect(toList());
    return RestResult.ok(organList);
  }
}
