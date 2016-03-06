package com.hyzx.xschool.domain;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_resource")
public class Resource implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 名称.
     */
	private String name;
	/**
	 * 资源路径：上传到OSS后的完整的URL.
     */
	private String path;
	/**
	 * 文件存储的路径：用于OSS图片压缩处理技术
	 */
	private String file;
	/**
	 * 类型.
     */
	@Column(name = "type", nullable = false)
	private Integer type;
	/**
	 * 备注.
     */
	private String remark;

	@Transient
	public boolean isLocalFile() {
			return !StringUtils.startsWithIgnoreCase(this.getPath(), "http://");
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "path", nullable = false, length = 50)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Column(name = "remark", length = 20)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
