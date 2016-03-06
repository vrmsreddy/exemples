package com.hyzx.xschool.service.impl;

import com.hyzx.xschool.domain.Comment;
import com.hyzx.xschool.domain.Organization;
import com.hyzx.xschool.domain.User;
import com.hyzx.xschool.domain.repository.CommentRepository;
import com.hyzx.xschool.domain.repository.OrganizationRepository;
import com.hyzx.xschool.domain.repository.UserRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.CommentService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.web.controller.request.CommentQueryFormBean;
import com.hyzx.xschool.web.controller.response.CommentInfo;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-12-06 15:28
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService {


  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private OrganizationRepository organizationRepository;

  @Override
  public Page<CommentInfo> findByPage(CommentQueryFormBean q) {
    // 分页取评论列表
    Pageable pageable =
      new PageRequest(q.getPageNo() - 1, q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
    Page<Comment> page = commentRepository
      .findByStatusAndCreateTimeBetween(q.getStatus(), q.getStartTime().getTime() + "",
        q.getEndTime().getTime() + "", pageable);
    if (!page.hasContent()) {
      return new PageImpl(Collections.emptyList());
    }

    // 批量获取该批评论的用户
    List<Long> userIds = page.getContent().stream().map(Comment::getUserId).collect(toList());
    List<User> users = userRepository.findAll(userIds);

    // 类目转换为map方便后续取值
    Map<Long, String> userMap = users.stream().collect(toMap(User::getId, User::getNickname));

    // 取评论所属的机构
    List<Long> orgIds =
      page.getContent().stream().map(Comment::getOrganizationId).collect(toList());
    List<Organization> organizations = organizationRepository.findAll(orgIds);
    Map<Long, String> orgMap =
      organizations.stream().collect(toMap(Organization::getId, Organization::getName));

    // 构造页面展示数据
    List<CommentInfo> list = page.getContent().stream().map(c -> {
      CommentInfo info = new CommentInfo();
      info.setId(c.getId());
      info.setNick(userMap.get(c.getUserId()));
      info.setOrganization(orgMap.get(c.getOrganizationId()));
      info.setContent(c.getContent());
      info.setCreateTime(
        new DateTime(Long.parseLong(c.getCreateTime())).toString(Constants.DATE_TIME_LONG_FORMAT));
      if (StringUtils.isNotBlank(c.getVerifyTime())) {
        info.setVerifyTime(new DateTime(Long.parseLong(c.getVerifyTime()))
          .toString(Constants.DATE_TIME_LONG_FORMAT));
      }
      info.setStatus(c.getStatus().toString());
      return info;
    }).collect(toList());

    return new PageImpl(list, pageable, page.getTotalElements());
  }

  @Override
  public void updateStatus(Long id, int status) {
    Comment comment = commentRepository.findOne(id);
    if (comment == null) {
      throw new BizException("机构不存在");
    }

    comment.setStatus(status);
    //TODO use enum
    if (status != -1) {
      comment.setVerifyTime(String.valueOf(new Date().getTime()));
    }
    commentRepository.save(comment);
  }
}
