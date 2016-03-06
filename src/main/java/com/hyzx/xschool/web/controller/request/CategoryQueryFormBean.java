package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by jack on 15-11-7.
 */
public class CategoryQueryFormBean extends PageQueryFormBean {
  private Long parentCateId;

  public Long getParentCateId() {
    return parentCateId;
  }

  public void setParentCateId(Long parentCateId) {
    this.parentCateId = parentCateId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
