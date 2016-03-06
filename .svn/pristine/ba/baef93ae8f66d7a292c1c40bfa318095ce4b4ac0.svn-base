package com.hyzx.xschool.service;

import com.hyzx.xschool.web.controller.request.CourseQueryFormBean;
import com.hyzx.xschool.web.controller.response.CourseInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jack on 15-11-20.
 */
public interface CourseService {
  Page<CourseInfo> findByPage(CourseQueryFormBean q);

  /**
   * 查看机构下的课程
   *
   * @param q
   * @return
   */
  List<CourseInfo> findByOrganId(CourseQueryFormBean q);
}
