package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The type Task.
 */
@Entity
@Table(name = "t_task")
public class Task implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 状态.
     */
    @Column(name = "status", nullable = false)
    private Integer status;
    /**
     * 难度.
     */
    @Column(name = "hard_type", nullable = false)
    private Integer hardType;
    /**
     * 详情.
     */
    private String detail;
    /**
     * 用户点击不想玩的次数.
     */
    private Long noPlayTimes;

    /**
     * 视频资源 id.
     */
    @Column(name = "video_resource_id", nullable = false)
    private Long videoResourceId;
    /**
     * 图片资源 id.
     */
    @Column(name = "video_pic_resource_id", nullable = false)
    private Long videoPicResourceId;
    /**
     * 题库 id.
     */
    private Long topicStoreId;
    /**
     * 创建时间.
     */
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public Task() {
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

    @Column(name = "name", nullable = false, length = 50)
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

    @Column(name = "detail", nullable = false, length = 2000)
    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "no_play_times", nullable = false)
    public Long getNoPlayTimes() {
        return this.noPlayTimes;
    }

    public void setNoPlayTimes(Long noPlayTimes) {
        this.noPlayTimes = noPlayTimes;
    }

    @Column(name = "create_time", nullable = false, length = 20)
    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getVideoResourceId() {
        return this.videoResourceId;
    }

    public void setVideoResourceId(Long videoResourceId) {
        this.videoResourceId = videoResourceId;
    }

    @Column(name = "topic_store_id", nullable = false)
    public Long getTopicStoreId() {
        return this.topicStoreId;
    }

    public void setTopicStoreId(Long topicStoreId) {
        this.topicStoreId = topicStoreId;
    }

    public Long getVideoPicResourceId() {
        return videoPicResourceId;
    }

    public void setVideoPicResourceId(Long videoPicResourceId) {
        this.videoPicResourceId = videoPicResourceId;
    }

    public Integer getHardType() {
        return hardType;
    }

    public void setHardType(Integer hardType) {
        this.hardType = hardType;
    }
}
