package com.hyzx.xschool.web.controller.response;

/**
 * Created by jack on 15-11-8.
 */
public class TopicInfo {
  private Long id;
  private String name;
  private Integer type;
  private String tpicStoreName;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getType() {
	return type;
}

public String getName() {
    return name;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setTpicStoreName(String tpicStoreName) {
    this.tpicStoreName = tpicStoreName;
  }

  public String getTpicStoreName() {
    return tpicStoreName;
  }
}
