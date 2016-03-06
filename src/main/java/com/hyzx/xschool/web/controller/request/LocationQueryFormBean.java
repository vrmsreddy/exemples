package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class LocationQueryFormBean extends PageQueryFormBean {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
