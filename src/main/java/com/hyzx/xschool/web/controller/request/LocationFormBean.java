package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jack on 15-11-1.
 */
@Deprecated
public class LocationFormBean {
  private Long id;
  private Long districtRegionId;

  @NotBlank(message = "city cannot be blank")
  private String city;

  @NotBlank(message = "district cannot be blank")
  private String district;

  @NotBlank(message = "name cannot be blank")
  private String name;

  private int order;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Long getDistrictRegionId() {
    return districtRegionId;
  }

  public void setDistrictRegionId(Long districtRegionId) {
    this.districtRegionId = districtRegionId;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
