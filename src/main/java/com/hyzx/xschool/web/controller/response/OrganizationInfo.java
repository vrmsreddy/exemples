package com.hyzx.xschool.web.controller.response;

import javax.persistence.Transient;

/**
 * Created by jack on 15-11-7.
 */
public class OrganizationInfo {

  private Long id;
  private String name;
  private String location;
  private String phone;
  private int shareNum;
  private int sort;
  private String status;
  private String profile;
  private String modifier;
 
  private String modifyTimeStr;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getShareNum() {
    return shareNum;
  }

  public void setShareNum(int shareNum) {
    this.shareNum = shareNum;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

public String getModifier() {
	return modifier;
}

public void setModifier(String modifier) {
	this.modifier = modifier;
}

public String getModifyTimeStr() {
	return modifyTimeStr;
}

public void setModifyTimeStr(String modifyTimeStr) {
	this.modifyTimeStr = modifyTimeStr;
}

 
}
