package com.hyzx.xschool.web.controller.request;

import java.util.List;

/**
 * Created by jack on 15-11-8.
 */
public class TopicFormBean {
  private Long id;
  private String name;
  private Long topicStoreId;
  private int type;
  List<TopicOption> options;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTopicStoreId() {
    return topicStoreId;
  }

  public void setTopicStoreId(Long topicStoreId) {
    this.topicStoreId = topicStoreId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public List<TopicOption> getOptions() {
    return options;
  }

  public void setOptions(List<TopicOption> options) {
    this.options = options;
  }


}
