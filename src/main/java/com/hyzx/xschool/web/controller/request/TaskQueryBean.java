package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by jack on 15-11-8.
 */
public class TaskQueryBean extends PageQueryFormBean{
  private String name;
  private Long topicStoreId=0L;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTopicStoreId() { 
    return topicStoreId;
  }

  public void setTopicStoreId(Long topicStoreId) {
    this.topicStoreId = topicStoreId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
