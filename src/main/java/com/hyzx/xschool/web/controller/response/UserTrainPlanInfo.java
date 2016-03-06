package com.hyzx.xschool.web.controller.response;

/**
 * Created by jack on 15-11-1.
 */
public class UserTrainPlanInfo {

  private String nick;
  private String mobile;
  private String trainPlanName;
  private int childAge;
  private int childOrder;
  private String completePledge;
  private String status;

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTrainPlanName() {
    return trainPlanName;
  }

  public void setTrainPlanName(String trainPlanName) {
    this.trainPlanName = trainPlanName;
  }

  public int getChildAge() {
    return childAge;
  }

  public void setChildAge(int childAge) {
    this.childAge = childAge;
  }

  public int getChildOrder() {
    return childOrder;
  }

  public void setChildOrder(int childOrder) {
    this.childOrder = childOrder;
  }

  public String getCompletePledge() {
    return completePledge;
  }

  public void setCompletePledge(String completePledge) {
    this.completePledge = completePledge;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
