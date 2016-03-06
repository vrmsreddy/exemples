package com.hyzx.xschool.service.impl;

import com.google.common.base.Strings;
import com.hyzx.xschool.domain.Category;
import com.hyzx.xschool.domain.Course;
import com.hyzx.xschool.domain.Organization;
import com.hyzx.xschool.domain.repository.CategoryRepository;
import com.hyzx.xschool.domain.repository.CourseRepository;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.service.CourseService;
import com.hyzx.xschool.web.controller.request.CourseQueryFormBean;
import com.hyzx.xschool.web.controller.response.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * Created by jack on 15-11-20.
 */
@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  CategoryRepository categoryRepository;
  @Autowired
  CourseRepository courseRepository;
  @Autowired
  OrganizationRepository organizationRepository;

  @Override
  public Page<CourseInfo> findByPage(CourseQueryFormBean q) {
    Pageable pageable =
        new PageRequest(q.getPageNo() - 1, q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
    Page<Course> page = null;
    if (Strings.isNullOrEmpty(q.getName())) {
      page = courseRepository.findAll(pageable);
    } else {
      page = courseRepository.findByNameContaining(q.getName(), pageable);
    }
    if (!page.hasContent()) {
      new PageImpl(Collections.emptyList());
    }
    List<CourseInfo> list = buildCourseInfos(page.getContent());
    return new PageImpl(list, pageable, page.getTotalElements());
  }

  @Override
  public List<CourseInfo> findByOrganId(CourseQueryFormBean q) {
    List<Course> courses = courseRepository.findByOrganizationId(q.getOrganId());
    if (CollectionUtils.isEmpty(courses)) {
      return Collections.emptyList();
    }
    return buildCourseInfos(courses);
  }

  public List<CourseInfo> buildCourseInfos(List<Course> courseList) {
    // 批量获取该批课程所对应的类目（父类目名字做了冗余，可以一并取出）
    List<Long> cateIds =
        courseList.stream().map(Course::getCategoryId).collect(toList());
    List<Category> categories = categoryRepository.findAll(cateIds);

    // 类目转换为map方便后续取值
    Map<Long, Category> cateMap =
        categories.stream().collect(toMap(Category::getId, (c)->c));

    // 获取父级分类
    Set<Long> parentCateIds = categories.stream().filter(c -> c.getParentCategoryId() != null)
        .map(Category::getParentCategoryId).collect(toSet());
    List<Category> parentCates = categoryRepository.findAll(parentCateIds);
    Map<Long, String> parentCateMap = parentCates.stream().collect(toMap(Category::getId, Category::getName));

    // 取课程所属的机构
    List<Long> orgIds =
        courseList.stream().map(Course::getOrganizationId).collect(toList());
    List<Organization> organizations = organizationRepository.findAll(orgIds);
    Map<Long, String> orgMap =
        organizations.stream().collect(toMap(Organization::getId, Organization::getName));

    // 构造页面展示数据
    return courseList.stream().map(c -> {
      CourseInfo info = new CourseInfo();
      info.setId(c.getId());
      info.setName(c.getName());
      // 设置二级分类：子分类
      Category category = cateMap.get(c.getCategoryId());
      info.setCategoryId(c.getCategoryId());
      info.setCateName(category.getName());
      // 设置一级分类：父分类
      Long parentCateId = category.getParentCategoryId();
      if (parentCateId != null) {
        info.setParentCateId(parentCateId);
        info.setParentCateName(parentCateMap.get(parentCateId));
      }
      // 设置机构
      info.setOrganizationId(c.getOrganizationId());
      info.setOrgnaztion(orgMap.get(c.getOrganizationId()));
      info.setFitAge(c.getFitAge());
      info.setPriority(c.getPriority());

      // TODO 列表时返回课程描述，字段内容多时会对性能有影响，后续需要重构
      info.setRemark(c.getRemark());
      return info;
    }).collect(toList());
  }
}
