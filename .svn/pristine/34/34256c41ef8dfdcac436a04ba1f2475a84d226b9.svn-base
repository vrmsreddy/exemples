package com.hyzx.xschool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 与oss相关的配置信息
 *
 * @author caoxudong
 * @since 0.1.0
 */
@ConfigurationProperties(prefix = "oss")
@Component
public class OSSConfig {

  private String accessKeyId;
  private String accessKeySecret;
  private String uploadEndpoint;
  private String downloadEndpoint;

  private String bucketName;
  private String realPath;
  private long downloadUrlExpires;

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  public String getUploadEndpoint() {
    return uploadEndpoint;
  }

  public void setUploadEndpoint(String uploadEndpoint) {
    this.uploadEndpoint = uploadEndpoint;
  }

  public String getDownloadEndpoint() {
    return downloadEndpoint;
  }

  public void setDownloadEndpoint(String downloadEndpoint) {
    this.downloadEndpoint = downloadEndpoint;
  }

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getRealPath() {
    return realPath;
  }

  public void setRealPath(String realPath) {
    this.realPath = realPath;
  }

  public long getDownloadUrlExpires() {
    return downloadUrlExpires;
  }

  public void setDownloadUrlExpires(long downloadUrlExpires) {
    this.downloadUrlExpires = downloadUrlExpires;
  }
  
}
