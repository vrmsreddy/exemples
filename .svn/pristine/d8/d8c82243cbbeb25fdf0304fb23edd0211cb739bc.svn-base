package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_evaluation", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Evaluation implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    /**
     * 名称.
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;
    /**
     * 状态.
     */
    @Column(name = "status", nullable = false)
    private Integer status;
    /**
     * 详情.
     */
    @Column(name = "detail", nullable = false, length = 2000)
    private String detail;
    /**
     * 题目数量.
     */
    @Column(name = "topic_num", nullable = false)
    private Integer topicNum;
    /**
     * 点击量.
     */
    @Column(name = "click_num", nullable = false)
    private Integer clickNum;

    // Constructors

    /**
     * default constructor
     */
    public Evaluation() {
    }

    // Property accessors
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }
}
