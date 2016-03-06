package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.domain.User;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.UserService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.util.session.SessionUtil;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.LoginFormBean;
import com.hyzx.xschool.web.controller.request.UserPassFormBean;
import com.hyzx.xschool.web.controller.response.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 基础的控制器，如默认的首页、辅助操作等.
 *
 * @author royguo@uworks.cc
 * @since 0.1.0
 */
@Api(value = "系统管理：首页、修改密码等", produces = "application/json")
@RestController
@RequestMapping("/")
public class BaseController {

  private static Logger log = LoggerFactory.getLogger(BaseController.class);

  @Autowired
  UserService userService;

  /**
   * 系统用户登录
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public RestResult<UserInfo> login(@RequestBody LoginFormBean u,HttpServletRequest request) {
    log.info("系统用户登录，input={}", u.toString());

    User user = userService.login(u.getUsername(), u.getPassword());
    SessionUtil.getSession(request).setAttribute(Constants.SESSION_USER_ID, user.getId());
    SessionUtil.getSession(request).setAttribute(Constants.SESSION_USER_NICKNANE, user.getNickname());

    UserInfo info = new UserInfo();
    info.setId(user.getId());
    info.setNickname(user.getNickname());
    info.setMobile(user.getMobile());
    info.setType(user.getType());
    return RestResult.ok(info);
  }

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public RestResult<Void> logout(HttpSession session) {
    session.invalidate();
    return RestResult.ok();
  }

  /**
   * 系统管理-修改密码
   */
  @ApiOperation(value = "系统管理->修改密码", notes = "修改当前登录用户的密码")
  @RequestMapping(value = "/sys/pass/save", method = RequestMethod.POST)
  public RestResult<String> modifyPass(@Valid @RequestBody UserPassFormBean pass
      , HttpServletRequest request) {

    log.info("用户修改系统密码");

    if (!StringUtils.equals(pass.getNewPass(), pass.getRepeatNewPass())) {
      throw new BizException("两次输入的新密码不一致");
    }

    HttpSession session = SessionUtil.getSession(request);
    Long userId = (Long) session.getAttribute(Constants.SESSION_USER_ID);
    pass.setUserId(userId);
    userService.modifyPass(pass);
    return RestResult.ok();
  }

  /**
   * HomePage
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<String> homePage(HttpServletRequest request) {
    log.info("进入首页");
    return ResponseEntity.ok("进入首页!");
  }

}
