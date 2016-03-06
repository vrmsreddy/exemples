package com.hyzx.xschool.web.controller.response;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录信息, 用来返回给客户端的新数据, 类似View Object.
 *
 * @author caoxudong
 * @since 0.1.0
 */
@Deprecated
public class UserLoginInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private long id;

  /**
   * 用户名
   */
  private String username;

  /**
   * 用户登录token
   */
  private String accessAuthToken;

  /**
   * 本次登录时间
   */
  private Date loginTime;

  /**
   * 上次登录时间
   */
  private Date lastLoginTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAccessAuthToken() {
    return accessAuthToken;
  }

  public void setAccessAuthToken(String accessAuthToken) {
    this.accessAuthToken = accessAuthToken;
  }

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

}
