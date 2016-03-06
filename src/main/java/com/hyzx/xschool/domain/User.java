package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_user", uniqueConstraints = @UniqueConstraint(columnNames = "mobile"))
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 手机号.
     */

	private String mobile;
	/**
	 * 昵称.
     */
	private String nickname;
	/**
	 * 密码：加密后字符串.
     */
	private String pwd;

	/**
	 * 状态.
     */
	@Column(name = "status", nullable = false)
	private Integer status;
	/**
	 * 类型, 1表示普通用户，2表示系统用户.
   */
	@Column(name = "type", nullable = false)
	private Integer type;
	/**
	 * 性别.
     */
	private String sex;
	/**
	 * 地址.
     */
	private String address;
	/**
	 * 创建时间.
     */
	private String createTime;
	/**
	 * 头像图片资源 id.
     */
	private Long resourceId;

	// Constructors

	/** default constructor */
	public User() {
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

	@Column(name = "mobile", unique = true, nullable = false, length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "pwd", nullable = false, length = 32)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Column(name = "nickname", length = 20)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "create_time", nullable = false, length = 20)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "resource_id")
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
