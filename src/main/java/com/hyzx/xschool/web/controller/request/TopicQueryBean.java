package com.hyzx.xschool.web.controller.request;

/**
 * Created by jack on 15-11-8.
 */
public class TopicQueryBean extends PageQueryFormBean{

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
}
