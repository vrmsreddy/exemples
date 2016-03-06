package com.hyzx.xschool.domain.enums;

public enum LocationStatus {
  ENABLE(1, "已开通"), DISABLE(0, "已禁止");

  private int code;
  private String label;

  private LocationStatus(Integer code, String label) {
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

  public static LocationStatus resolve(int code) {
    switch (code) {
      case 1:
        return ENABLE;
      case 0:
        return DISABLE;
      default:
        return null;
    }
  }

  public static LocationStatus resolve(Integer code) {
    if (null == code) {
      return null;
    } else {
      return resolve(code.intValue());
    }
  }
}
