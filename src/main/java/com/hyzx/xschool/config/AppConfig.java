package com.hyzx.xschool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 与当前应用相关的配置信息
 *
 * @author caoxudong
 * @since 0.1.0
 */
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {

  private String appName;
  private Boolean allowCrossDomainAccess;
  private Boolean allowGenerateApi;

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public Boolean getAllowCrossDomainAccess() {
    return allowCrossDomainAccess;
  }

  public void setAllowCrossDomainAccess(Boolean allowCrossDomainAccess) {
    this.allowCrossDomainAccess = allowCrossDomainAccess;
  }

  public Boolean getAllowGenerateApi() {
    return allowGenerateApi;
  }

  public void setAllowGenerateApi(Boolean allowGenerateApi) {
    this.allowGenerateApi = allowGenerateApi;
  }
  

}
