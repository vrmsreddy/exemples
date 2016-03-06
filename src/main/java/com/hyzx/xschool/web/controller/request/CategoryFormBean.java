package com.hyzx.xschool.web.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by jack on 15-11-7.
 */
public class CategoryFormBean {
  private Long id;
  @NotBlank(message = "名称不能为空")
  private String name;
  private Long parentId;
  private int order;

  @NotNull(message = "必须先上传图片")
  private Long resourceId;
  private boolean homePageShow;

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

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Long getResourceId() {
    return resourceId;
  }

  public void setResourceId(Long resourceId) {
    this.resourceId = resourceId;
  }

  public boolean isHomePageShow() {
    return homePageShow;
  }

  public void setHomePageShow(boolean homePageShow) {
    this.homePageShow = homePageShow;
  }

  @Override
  public String toString() {
    return new org.apache.commons.lang.builder.ToStringBuilder(this)
        .toString();
  }
}
