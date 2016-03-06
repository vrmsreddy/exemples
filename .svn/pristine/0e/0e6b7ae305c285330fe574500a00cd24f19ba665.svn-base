package com.hyzx.xschool.domain.enums;

public enum UserStatus {
  NORMAL(1, "正常"), DISABLE(0, "禁用");

  private Integer code;
  private String label;

  private UserStatus(Integer code, String label) {
    this.code = code;
    this.label = label;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public static UserStatus resolve(int code) {
    switch (code) {
      case 1:
        return NORMAL;
      case 0:
        return DISABLE;
      default:
        return null;
    }
  }

  public static UserStatus resolve(Integer code) {
    if (null == code) {
      return null;
    } else {
      return resolve(code.intValue());
    }
  }
}
