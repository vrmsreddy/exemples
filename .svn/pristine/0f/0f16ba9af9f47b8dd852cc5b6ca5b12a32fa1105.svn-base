package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by jack on 15-11-7.
 */
public class CommentQueryFormBean extends PageQueryFormBean{

  @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;
  @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;
  private Integer status;

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .toString();
  }
}
