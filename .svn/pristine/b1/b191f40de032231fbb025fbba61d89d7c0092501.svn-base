package com.hyzx.xschool.web.controller.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jack on 15-11-21.
 */
public class UserPassFormBean {
  private Long userId;
  @NotBlank(message = "oldPass cannot be blank")
  private String oldPass;
  @NotBlank(message = "repeatNewPass cannot be blank")
  private String repeatNewPass;
  @NotBlank(message = "newPass cannot be blank")
  private String newPass;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getOldPass() {
    return oldPass;
  }

  public void setOldPass(String oldPass) {
    this.oldPass = oldPass;
  }

  public String getRepeatNewPass() {
    return repeatNewPass;
  }

  public void setRepeatNewPass(String repeatNewPass) {
    this.repeatNewPass = repeatNewPass;
  }

  public String getNewPass() {
    return newPass;
  }

  public void setNewPass(String newPass) {
    this.newPass = newPass;
  }
}
