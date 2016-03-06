package com.hyzx.xschool.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_organization", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Organization implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 名字.
     */
    private String name;

    /**
     * 状态.
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 热度.
     */
    @Column(name = "heat", nullable = false)
    private Integer heat;

    /**
     * 联系电话.
     */
    private String phone;
    /**
     * 经度
     */
    @Column(name = "longitude")
    private String longitude;
    /**
     * 纬度
     */
    @Column(name = "latitude")
    private String latitude;
    /**
     * 位置 id.
     */
    private Long locationId;
    /**
     * 详细地址.
     */
    private String addressDetail;
    /**
     * 分享数.
     */
    private Long shareNum;
    /**
     * 分享URL.
     */
    @Column(name = "shareUrl")
    private String shareUrl;
    /**
     * 简介.
     */
    private String profile;
    /**
     * 详情.
     */
    private String detail;
    /**
     * 创建时间.
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间.
     */
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "modifier")
    private String modifier;
    // Constructors

    /**
     * default constructor
     */
    public Organization() {
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

    @Column(name = "name", unique = true, nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "location_id")
    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Column(name = "address_detail", nullable = false, length = 200)
    public String getAddressDetail() {
        return this.addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Column(name = "share_num", nullable = false)
    public Long getShareNum() {
        return this.shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Column(name = "profile", length = 50)
    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Column(name = "detail", length = 5000)
    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
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

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
    
	/*
	 * 连接地址
	 */
    @Column(name = "con_address")
    private String conAddress;

	public String getConAddress() {
		return conAddress;
	}

	public void setConAddress(String conAddress) {
		this.conAddress = conAddress;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
    
}
