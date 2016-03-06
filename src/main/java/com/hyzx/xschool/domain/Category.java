package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The type Category.
 */
@Entity
@Table(name = "t_category")
public class Category implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  /**
   * 名称
   */
  private String name;
  /**
   * 优先级
   */
  @Column(name = "priority", nullable = false)
  private Integer priority;
  /**
   * 是否首页展示
   */
  private String isHomeShow;
  /**
   * 说明
   */
  private String explanation;
  /**
   * 创建时间
   */
  private String createTime;
  /**
   * 父类目 id.
   */
  private Long parentCategoryId;
  /**
   * 父类目 name.
   */
//  @Column(name = "parent_category_name")
  @Transient
  private String parentCategoryName;

  /**
   * 图片资源 id.
   */
  @Column(name = "resource_id", nullable = false)
  private Long resourceId;

  /**
   * 页面显示用：分类图片URL
   */
  @Transient
  private String picUrl;

  @Transient
  private String createTimeStr;

  @Transient
  public String getPicUrl() {
    return picUrl;
  }

  @Transient
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  /**
   * 是否父级分类
   * @return
   */
  @Transient
  public boolean isParent() {
    return this.getParentCategoryId() == null;
  }

  // Property accessors
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Column(name = "is_home_show", nullable = false, length = 1)
  public String getIsHomeShow() {
    return this.isHomeShow;
  }

  public void setIsHomeShow(String isHomeShow) {
    this.isHomeShow = isHomeShow;
  }

  @Column(name = "explanation", length = 50)
  public String getExplanation() {
    return this.explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  @Column(name = "create_time", nullable = false, length = 20)
  public String getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  @Column(name = "parent_category_id")
  public Long getParentCategoryId() {
    return this.parentCategoryId;
  }

  public void setParentCategoryId(Long parentCategoryId) {
    this.parentCategoryId = parentCategoryId;
  }

  public Long getResourceId() {
    return this.resourceId;
  }

  public void setResourceId(Long resourceId) {
    this.resourceId = resourceId;
  }

  @Transient
  public String getParentCategoryName() {
    return parentCategoryName;
  }

  @Transient
  public void setParentCategoryName(String parentCategoryName) {
    this.parentCategoryName = parentCategoryName;
  }

  public void setCreateTimeStr(String createTimeStr) {
    this.createTimeStr = createTimeStr;
  }

  @Transient
  public String getCreateTimeStr() {
    return createTimeStr;
  }
}
