package com.hyzx.xschool.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * 文件工具类
 * 
 * @author jiangyujiang@uworks.cc
 */
public class FileUtil {
  private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

  private static String chartset = "UTF-8";

  /**
   * 获取文件扩展名
   * @param fileName
   * @return
   */
  public static String getExtension(String fileName) {
    if (StringUtils.isBlank(fileName)) {
      return "";
    }
    return fileName.substring(fileName.lastIndexOf("."));
  }

  /**
   * 获取文件分隔符
   * 
   * @return
   */
  public static String getFileSeparator() {
    return File.separator;
  }

  /**
   * 读取内容
   * 
   * @param in
   * @return
   */
  public static String readString(InputStream in) {
    if (in == null) {
      return "";
    }

    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    try {
      for (int n; (n = in.read(b)) != -1;) {
        out.append(new String(b, 0, n, chartset));
      }
    } catch (Exception e) {
      logger.info("", e);
    }
    return out.toString();
  }

}
