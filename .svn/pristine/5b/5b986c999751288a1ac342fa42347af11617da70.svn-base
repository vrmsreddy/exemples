package com.hyzx.xschool.util;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hyzx.xschool.config.OSSConfig;

import com.aliyun.oss.OSSClient;

/**
 * OSS工具类
 *
 * @author caoxudong
 * @since 0.1.0
 */
@Component
public class OSSUtils {

  @Autowired OSSConfig ossConfig;
  @Autowired OSSClient downloadOSSClient;
  
  /**
   * 将给定的地址转换为可访问的oss访问路径
   * @param path    相对地址
   * @return        oss访问路径
   */
  public URL generateOSSDownloadURL(String path) {
    String bucketName = ossConfig.getBucketName();
    long expires = ossConfig.getDownloadUrlExpires();
    long now = System.currentTimeMillis();
    return downloadOSSClient.generatePresignedUrl(bucketName, path, new Date(now + expires));
  }
  
}
