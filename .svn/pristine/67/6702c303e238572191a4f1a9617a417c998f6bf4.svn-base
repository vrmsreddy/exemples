package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_evaluation_topic_store")
public class EvaluationTopicStore implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  /**
   * 评测 id.
   */
  private Long evaluationId;
  /**
   * 题库 id.
   */
  private Long topicStoreId;
  /**
   * 题目数量.
   */
  @Column(name = "topic_num", nullable = false)
  private Integer topicNum;
  /**
   * 优先级.
   */
  private Long priority;

  // Constructors

  /**
   * default constructor
   */
  public EvaluationTopicStore() {
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

  @Column(name = "evaluation_id", nullable = false)
  public Long getEvaluationId() {
    return this.evaluationId;
  }

  public void setEvaluationId(Long evaluationId) {
    this.evaluationId = evaluationId;
  }

  @Column(name = "topic_store_id", nullable = false)
  public Long getTopicStoreId() {
    return this.topicStoreId;
  }

  public void setTopicStoreId(Long topicStoreId) {
    this.topicStoreId = topicStoreId;
  }

  public Integer getTopicNum() {
    return topicNum;
  }

  public void setTopicNum(Integer topicNum) {
    this.topicNum = topicNum;
  }

  @Column(name = "priority", nullable = false)
  public Long getPriority() {
    return this.priority;
  }

  public void setPriority(Long priority) {
    this.priority = priority;
  }

}
