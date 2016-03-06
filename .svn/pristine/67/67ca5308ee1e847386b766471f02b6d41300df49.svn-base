package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_feedback")
public class Feedback implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户 id.
     */
    private Long userId;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 反馈信息.
     */
    private String info;
    /**
     * 联系方式.
     */
    private String contact;
    /**
     * 反馈时间.
     */
    private String createTime;
    /**
     * 处理时间.
     */
    private String dealTime;

    /**
     * 页面显示用，如果直接使用createTime这个持久化字段，会自动修改DB中数据【JPA巨坑！】
     */
    @Transient
    private String createTimeStr;

    @Transient
    public String getCreateTimeStr() {
        return createTimeStr;
    }

    @Transient
    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    // Constructors

    /**
     * default constructor
     */
    public Feedback() {
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

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "contact", nullable = false, length = 50)
    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "create_time", nullable = false, length = 20)
    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "deal_time", length = 20)
    public String getDealTime() {
        return this.dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
