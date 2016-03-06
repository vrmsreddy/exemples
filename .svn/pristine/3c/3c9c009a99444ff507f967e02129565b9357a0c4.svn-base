package com.hyzx.xschool.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Sets;
import com.hyzx.xschool.domain.User;
import com.hyzx.xschool.domain.enums.UserType;
import com.hyzx.xschool.service.UserService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.util.ServletUtil;
import com.hyzx.xschool.util.session.SessionUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.RestReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * 权限控制拦截器.
 */
@Component
public class AuthorityControlInterceptor extends HandlerInterceptorAdapter {
  private static final Logger log = LoggerFactory.getLogger(AuthorityControlInterceptor.class);

  @Autowired
  UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestURI = request.getRequestURI();
    log.info("用户访问鉴权 : URI = {}", requestURI);

    //TODO 拦截器配置exclude不生效，临时解决下
    Set<String> excludes = Sets.newHashSet();
    excludes.add("/organization/list");
    excludes.add("/organization/*");
    excludes.add("/organization/save");
    excludes.add("/organization/*/update/*");
    excludes.add("/organization/*/pic/list");
    excludes.add("/organization/pic/remove");
    excludes.add("/organization/pic/save");
    excludes.add("/organization/valid/list");
    excludes.add("/organization/course/**");
    excludes.add("/logout");
    excludes.add("/login");

    //TODO 拦截器配置exclude不生效，临时解决下
    if (excludes.contains(requestURI)) {
      return true;
    }

    HttpSession session = SessionUtil.getSession(request);
    Long userIdObj = (Long) session.getAttribute(Constants.SESSION_USER_ID);
    if (userIdObj == null) {
      handleIllegalRequest(request, response, RestReturnCode.INVALID_SESSION);
      return false;
    }

    User user = userService.findById(userIdObj);
    if (user == null) {
      handleIllegalRequest(request, response, RestReturnCode.INVALID_SESSION);
      return false;
    }

    // 只有公司内部管理员类型的用户才可以访问所有功能
    if ((user.getType() == UserType.NORMAL.getCode())) {
      return true;
    }

    handleIllegalRequest(request, response, RestReturnCode.INVALID_SESSION);
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  /**
   * 处理非法请求：未认证、会话过期等
   *
   * @param failedReturnCode 针对Ajax请求返回的错误码
   * @throws IOException
   * @author youblade
   * @since v0.1
   */
  protected static void handleIllegalRequest(HttpServletRequest request,
      HttpServletResponse response,
      int failedReturnCode) throws IOException {
    // 访问其他路径时，转向登录页
    if (!ServletUtil.isAjax(request)) {
      response.sendRedirect("/html/login.html");
    } else {
      // 针对Ajax请求返回特定格式的消息
      String json = JSON.toJSONString(new RestResult(failedReturnCode, "", "/login"));
      response.getWriter().write(json);
      return;
    }
  }
}
