package com.hyzx.xschool.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jack on 15-11-22.
 */
public class ServletUtil {

  /**
   * 判断是否Ajax请求.
   *
   * @param request
   * @return
   * @author youblade
   * @created 2015年5月4日 下午3:31:24
   * @since v0.1
   */
  public static boolean isAjax(HttpServletRequest request) {
    return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest"
        .equals(request.getHeader(
            "X-Requested-With").toString()));
  }

  /**
   * 获取客户端真实IP.
   *
   * @param request
   * @return
   * @author youblade
   * @created 2015年5月4日 下午3:34:07
   * @since v0.1
   */
  public static String getRealIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Real-IP");
    if (StringUtils.isNotBlank(ip)) {
      return ip;
    }
    return request.getRemoteAddr();
  }
}
