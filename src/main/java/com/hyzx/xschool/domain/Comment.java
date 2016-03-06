package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_comment")
public class Comment implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户id.
     */
    private Long userId;
    /**
     * 机构id.
     */
    private Long organizationId;
    /**
     * 内容.
     */
    private String content;

    /**
     * 状态.
     */
    @Column(name = "status", nullable = false)
    private Integer status;
    /**
     * 创建时间.
     */
    private String createTime;
    /**
     * 审核时间.
     */
    private String verifyTime;

    // Constructors

    /**
     * default constructor
     */
    public Comment() {
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

    @Column(name = "organization_id", nullable = false)
    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "content", nullable = false, length = 200)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "create_time", nullable = false, length = 20)
    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Column(name = "verify_time", length = 20)
    public String getVerifyTime() {
        return this.verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

}
