package com.hyzx.xschool.domain.enums;

/**
 * 开通状态
 */
public enum OpenStatus {
  VALID(1, "已开通"), INVALID(0, "已禁用");

  private int code;
  private String label;

  private OpenStatus(Integer code, String label) {
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

  public static OpenStatus resolve(int code) {
    switch (code) {
      case 1:
        return VALID;
      case 0:
        return INVALID;
      default:
        return null;
    }
  }

  public static OpenStatus resolve(Integer code) {
    if (null == code) {
      return null;
    } else {
      return resolve(code.intValue());
    }
  }
}
