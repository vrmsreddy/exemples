package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by jack on 15-11-8.
 */
public class TaskFormBean {
  private Long id;
  @NotBlank(message = "任务名称不能为空")
  private String name;
  private Long topicStoreId;
  private int hardType;
  @NotBlank(message = "任务详情不能为空")
  private String detail;

//  private Long videoResourceId;
  /**
   * 在线视频外链URL
   */
  @NotBlank(message = "视频链接不能为空")
  private String videoPath;

  private Long picRescoureId;


  // 编辑界面显示用字段
  private String picPath;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setTopicStoreId(Long topicStoreId) {
    this.topicStoreId = topicStoreId;
  }

  public Long getTopicStoreId() {
    return topicStoreId;
  }

  public void setHardType(int hardType) {
    this.hardType = hardType;
  }

  public int getHardType() {
    return hardType;
  }


  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getDetail() {
    return detail;
  }

  //  public Long getVideoResourceId() {
//    return videoResourceId;
//  }
//
//  public void setVideoResourceId(Long videoResourceId) {
//    this.videoResourceId = videoResourceId;
//  }

  public Long getPicRescoureId() {
    return picRescoureId;
  }

  public void setPicRescoureId(Long picRescoureId) {
    this.picRescoureId = picRescoureId;
  }

  public String getVideoPath() {
    return videoPath;
  }

  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }

  public String getPicPath() {
    return picPath;
  }

  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
