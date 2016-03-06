package com.hyzx.xschool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_user_organization")
public class UserOrganization implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 用户 id.
     */
	private Long userId;
	/**
	 * 机构 id.
     */
	private Long organizationId;

	// Constructors

	/** default constructor */
	public UserOrganization() {
	}

	/** full constructor */
	public UserOrganization(Long userId, Long organizationId) {
		this.userId = userId;
		this.organizationId = organizationId;
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

}
