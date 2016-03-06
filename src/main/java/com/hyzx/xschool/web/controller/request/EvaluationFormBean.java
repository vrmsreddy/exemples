package com.hyzx.xschool.web.controller.request;

import com.hyzx.xschool.domain.EvaluationTopicStore;

import java.util.List;

/**
 * Created by jack on 15-11-8.
 */
public class EvaluationFormBean {
  private Long id;
  private String name;
  private String detail;
  private List<EvaluationTopicStore> topicStoreList;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<EvaluationTopicStore> getTopicStoreList() {
    return topicStoreList;
  }

  public void setTopicStoreList(List<EvaluationTopicStore> topicStoreList) {
    this.topicStoreList = topicStoreList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


}
