package com.hyzx.xschool.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_organization_resource", uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "is_main" }))
public class OrganizationResource implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 机构 id.
     */
	private Long organizationId;
	/**
	 * 图片资源 id.
     */
	private Long resourceId;
	/**
	 * 是否主图.
     */
	private String isMain;
	/**
	 * 排序优先级.
     */
	private Long priority;

	// Constructors

	/** default constructor */
	public OrganizationResource() {
	}

	/** full constructor */
	public OrganizationResource(Long organizationId, Long resourceId,
			String isMain, Long priority) {
		this.organizationId = organizationId;
		this.resourceId = resourceId;
		this.isMain = isMain;
		this.priority = priority;
	}

  /**
   * 是否主图
   *
   * @return
   */
  @Transient
  public boolean isMajorPic() {
    return org.apache.commons.lang.StringUtils.equals("1", this.getIsMain());
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

	@Column(name = "organization_id", nullable = false)
	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "resource_id", nullable = false)
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "is_main", nullable = false, length = 1)
	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	@Column(name = "priority", nullable = false)
	public Long getPriority() {
		return this.priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

}
