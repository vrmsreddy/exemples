package com.hyzx.xschool.service;

import com.hyzx.xschool.web.controller.request.CommentQueryFormBean;
import com.hyzx.xschool.web.controller.response.CommentInfo;
import org.springframework.data.domain.Page;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-12-06 15:27
 */
public interface CommentService {
  Page<CommentInfo> findByPage(CommentQueryFormBean q);

  void updateStatus(Long id, int status);
}
