package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_course")
public class Course implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 优先级.
     */
    @Column(name = "priority", nullable = false)
    private Integer priority;
    /**
     * 适合年龄.
     */
    private String fitAge;
    /**
     * 描述.
     */
    private String remark;
    /**
     * 课程类目id.
     */
    private Long categoryId;
    /**
     * 机构id.
     */
    private Long organizationId;

    // Constructors

    /**
     * default constructor
     */
    public Course() {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Column(name = "fit_age", nullable = false, length = 10)
    public String getFitAge() {
        return this.fitAge;
    }

    public void setFitAge(String fitAge) {
        this.fitAge = fitAge;
    }

    @Column(name = "remark", length = 500)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "category_id", nullable = false)
    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "organization_id", nullable = false)
    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

}
