package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户登录时的表单数据
 *
 * @author caoxudong
 * @since 0.1.0
 */
public class LoginFormBean {

  @NotBlank(message = "Username cannot be blank")
  private String username;
  @NotBlank(message = "Password cannot be blank")
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
