package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by jack on 15-11-8.
 */
public class ArticleQueryVo extends PageQueryFormBean {

  private String keyword;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).toString();
  }
}
