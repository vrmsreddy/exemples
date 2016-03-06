package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 基础行政区地址数据
 * Created by jack on 15-11-5.
 */
@Entity
@Table(name = "t_region")
public class Region {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 名称.
     */
    @Column(name = "name")
    private String name;
    /**
     * 父级地区 id.
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 类型：1国家，2省直辖市，3城市，4区县.
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 邮政编码.
     */
    @Column(name = "zip")
    private String zip;

    /**
     * 【已废弃】是否开通：仅在城市级别有效
     */
    @Deprecated
    @Column(name = "is_opened")
    private Boolean isOpened;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(Boolean isOpened) {
        this.isOpened = isOpened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Region region = (Region) o;

        return id.equals(region.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
