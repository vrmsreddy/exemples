package com.hyzx.xschool.web.controller.response;

/**
 * Created by jack on 15-11-7.
 */
public class OrgPicInfo {

  /**
   * 机构资源OrganizationResource记录的ID
   */
  private Long id;
  private Long resourceId;
  private String path;
  private Boolean major;
  private int order;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getResourceId() {
    return resourceId;
  }

  public void setResourceId(Long resourceId) {
    this.resourceId = resourceId;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Boolean getMajor() {
    return major;
  }

  public void setMajor(Boolean major) {
    this.major = major;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
