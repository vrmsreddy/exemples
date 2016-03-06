package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_user_train_plan_record")
public class UserTrainPlanRecord implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  /**
   * 用户 id.
   */
  private Long userId;
  /**
   * 测评 id.
   */
  @Column(name = "evaluation_id")
  private Long evaluationId;
  /**
   * 测评 name.
   */
  @Column(name = "evaluation_name")
  private String evaluationName;
  /**
   * The Child birthday.
   */
  private String childBirthday;
  /**
   * The Child top.
   */
  private Integer childTop;
  /**
   * The Status.
   */
  private Long status;
  /**
   * The Complete degree.
   */
  private String completeDegree;

  /**
   * default constructor
   */
  public UserTrainPlanRecord() {
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

  @Column(name = "user_id", nullable = false)
  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "child_birthday", nullable = false, length = 20)
  public String getChildBirthday() {
    return this.childBirthday;
  }

  public void setChildBirthday(String childBirthday) {
    this.childBirthday = childBirthday;
  }

  @Column(name = "child_top", nullable = false)
  public Integer getChildTop() {
    return this.childTop;
  }

  public void setChildTop(Integer childTop) {
    this.childTop = childTop;
  }

  @Column(name = "status", nullable = false)
  public Long getStatus() {
    return this.status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  @Column(name = "complete_degree", nullable = false, length = 20)
  public String getCompleteDegree() {
    return this.completeDegree;
  }

  public void setCompleteDegree(String completeDegree) {
    this.completeDegree = completeDegree;
  }

  public Long getEvaluationId() {
    return evaluationId;
  }

  public void setEvaluationId(Long evaluationId) {
    this.evaluationId = evaluationId;
  }

  public String getEvaluationName() {
    return evaluationName;
  }

  public void setEvaluationName(String evaluationName) {
    this.evaluationName = evaluationName;
  }


}
