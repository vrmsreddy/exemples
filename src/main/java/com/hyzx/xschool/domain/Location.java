package com.hyzx.xschool.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_location", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Location implements java.io.Serializable {

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
     * 优先级.
     */
    @Column(name = "priority", nullable = false)
    private Integer priority;

    /**
     * 一级行政区（省、直辖市）的region id.
     */
    @Column(name = "province_id")
    private Long provinceId;
    /**
     * 二级行政区（城市）的region id.
     */
    @Column(name = "city_id")
    private Long cityId;
    /**
     * 三级行政区（区县）的region id.
     */
    @Column(name = "region_id")
    private Long regionId;
    /**
     * 二级行政区（城市）：冗余字段.
     */
    @Column(name = "city")
    private String city;
    /**
     * 三级行政区（区县）：冗余字段.
     */
    @Column(name = "county")
    private String county;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间.
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    // Constructors

    /**
     * default constructor
     */
    public Location() {
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

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
