package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class UserFormBean {
  // 如果id不为空，则只更新不为空的字段，否则整个保存.
  private Long id;
  private String nick;
  /**
   * 性别：男为1，女为2
   */
  private int gender;
  private String mobile;
  private String address;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
