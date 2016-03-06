package com.hyzx.xschool.web.controller.organization;

import com.hyzx.xschool.domain.Course;
import com.hyzx.xschool.domain.repository.CategoryRepository;
import com.hyzx.xschool.domain.repository.CourseRepository;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.CourseService;
import com.hyzx.xschool.util.ValidateUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.CourseFormBean;
import com.hyzx.xschool.web.controller.request.CourseQueryFormBean;
import com.hyzx.xschool.web.controller.response.CourseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 机构管理-课程管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "机构管理->课程管理", produces = "application/json")
@RestController
@RequestMapping("/organization/course")
public class CourseController {

  private static Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

  @Autowired
  CourseRepository courseRepository;
  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  CourseService courseService;
  @Autowired
  OrganizationRepository organizationRepository;

  /**
   * 查看课程列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "课程管理：课程列表", notes = "分页获取课程数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<CourseInfo> list(@RequestBody CourseQueryFormBean q) {
    LOGGER.info("查看课程 -> {}", q.getName());

    // 存在机构ID时，仅返回该机构下的课程
    if (q.getOrganId() != null) {
      List<CourseInfo> courseInfos = courseService.findByOrganId(q);
      return RestResult.okPage(courseInfos, courseInfos.size());
    }

    // 分页取课程列表
    Page<CourseInfo> page = courseService.findByPage(q);
    return RestResult.ok(page);
  }

  /**
   * 编辑课程.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "课程管理-课程列表：编辑", notes = "获取待编辑的课程信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<Course> detail(@PathVariable("id") Long id) {
    Course Course = courseRepository.findOne(id);
    return RestResult.ok(Course);
  }

  /**
   * 保存课程.
   *
   * @param course
   * @return
   */
  @ApiOperation(value = "课程管理-课程列表：编辑->保存", notes = "保存课程信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@Valid @RequestBody CourseFormBean course) {
    LOGGER.info("保存课程 -> {}", course.toString());
    String oldName = course.getOldName();
    String name = course.getName();
    if (!StringUtils.equals(name, oldName) && courseRepository.findByNameAndOrganizationId(name,course.getOrgId()) != null) {
    	 throw new BizException("所选机构下已经存在名为［"+name+"］的课程，请更换一个课程名");
	}
    
    int order =  course.getOrder();
    Validate.isTrue(ValidateUtil.range(order,1,100), "排序值必须在1-100之间"); //organization.getHeat()
    Validate.notEmpty(course.getName(),"课程名不能为空");
    Validate.notEmpty(course.getDescribe(),"课程描述不能为空");
    Course courseToSave = null;
    if (course.getId() != null) {
      courseToSave = courseRepository.findOne(course.getId());
      if (courseToSave == null) {
        throw new BizException("课程不存在");
      }
    } else {
      courseToSave = new Course();
    }

    courseToSave.setName(course.getName());
    courseToSave.setCategoryId(course.getCateId());
    courseToSave.setFitAge(course.getAge());
    courseToSave.setOrganizationId(course.getOrgId());
    courseToSave.setPriority(course.getOrder());
    courseToSave.setRemark(course.getDescribe());
    courseRepository.save(courseToSave);
    return RestResult.ok();
  }

  /**
   * 删除课程.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "课程管理-课程列表：删除", notes = "删除课程记录")
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
  public RestResult<Void> remove(@PathVariable("id") Long id) {
    courseRepository.delete(id);
    return RestResult.ok();
  }
  
  /**
   * 验证是否重名.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "课程管理-课程编辑：验证", notes = "验证是否重名")
  @ResponseBody
  @RequestMapping(value = "/checkName", method = RequestMethod.GET)
  public String checkName(String oldName,String name,long organizationId) {
	  if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && courseRepository.findByNameAndOrganizationId(name,organizationId) == null) {
			return "true";
		}
		return "false";
   
  }
}
