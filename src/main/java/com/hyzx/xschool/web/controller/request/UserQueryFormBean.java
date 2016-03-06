package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class UserQueryFormBean extends PageQueryFormBean {
  private String nick;
  private String mobile;

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
