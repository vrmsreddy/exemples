package com.hyzx.xschool.web.controller.request;

import org.hibernate.validator.constraints.Range;

/**
 * Created by jack on 15-10-31.
 */
public class PageQueryFormBean {

  @Range(min = 1, max = 1000, message = "pageNo must be greater than 0")
  protected int pageNo;
  @Range(min = 1, max = 100, message = "pageNo must be greater than 0")
  protected int pageSize;

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
