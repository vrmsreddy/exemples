package com.hyzx.xschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;

/**
 * 生成特殊bean的工厂bean, 有些第三方的Bean没有标注@component, 需要手工初始化.
 *
 * @author caoxudong
 * @since 0.1.0
 */
@Configuration
public class AppBeanFactary {

  @Autowired
  OSSConfig ossConfig;

  /**
   * 存储-下载
   * 
   * @return
   */
  @Bean(name = "downloadOSSClient")
  public OSSClient downloadOSSClient() {
    return new OSSClient(ossConfig.getDownloadEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }

  /**
   * 存储-上传
   * 
   * @return
   */
  @Bean(name = "uploadOSSClient")
  public OSSClient uploadOSSClient() {
    return new OSSClient(ossConfig.getUploadEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }
}
