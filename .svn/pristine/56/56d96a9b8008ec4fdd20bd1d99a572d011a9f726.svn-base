package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_topic")
public class Topic implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 类型
     */
    @Column(name = "type", nullable = false)
    private Integer type;
    /**
     * 题目选项：json格式数据.
     */
    private String options;
    /**
     * 题库id.
     */
    private Long topicStoreId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 500)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "options", nullable = false, length = 1000)
    public String getOptions() {
        return this.options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Column(name = "topic_store_id", nullable = false)
    public Long getTopicStoreId() {
        return this.topicStoreId;
    }

    public void setTopicStoreId(Long topicStoreId) {
        this.topicStoreId = topicStoreId;
    }

}
