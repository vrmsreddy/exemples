package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_topic_store", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
    @UniqueConstraint(columnNames = "train_plan_name") })
public class TopicStore implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 名称.
     */
    private String name;

    /**
     * 题目数量.
     */
    @Column(name = "topic_num", nullable = false)
    private Integer topicNum;
    /**
     * 任务数量.
     */
    @Column(name = "task_num", nullable = false)
    private Integer taskNum;
    /**
     * 优势说明.
     */
    private String goodDescription;
    /**
     * 劣势说明.
     */
    private String badDescription;
    /**
     * 培养方案名称.
     */
    private String trainPlanName;

    // Constructors

    /**
     * default constructor
     */
    public TopicStore() {
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

    @Column(name = "name", unique = true, nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Column(name = "good_description", nullable = false, length = 500)
    public String getGoodDescription() {
        return this.goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    @Column(name = "bad_description", nullable = false, length = 500)
    public String getBadDescription() {
        return this.badDescription;
    }

    public void setBadDescription(String badDescription) {
        this.badDescription = badDescription;
    }

    @Column(name = "train_plan_name", unique = true, nullable = false, length = 200)
    public String getTrainPlanName() {
        return this.trainPlanName;
    }

    public void setTrainPlanName(String trainPlanName) {
        this.trainPlanName = trainPlanName;
    }

}
