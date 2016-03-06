package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.web.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 异常响应处理。将异常包装为固定的格式并返回。
 * <p>
 * <p>
 * 指定格式为{@link ResponseEntity}。
 *
 * @author caoxudong
 * @see ResponseEntity
 * @since 0.1.0
 */
@ControllerAdvice
public class ControllerAssist {

  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAssist.class);

  /**
   * 处理服务器端报错
   *
   * @param request 请求对象
   * @param e       异常对象
   * @return
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public RestResult<String> handel(HttpServletRequest request, Exception e) {
    LOGGER.error(e.getMessage(), e);
    return RestResult.error(e.getMessage());
  }

  /**
   * 处理客户端请求相关错误
   *
   * @param request 请求对象
   * @param e       异常对象
   * @return
   */
  @ExceptionHandler({IllegalArgumentException.class, ServletRequestBindingException.class,
                      MissingServletRequestParameterException.class,
                      HttpMediaTypeNotSupportedException.class})
  @ResponseBody
  public RestResult<String> handleIllegalArgumentException(HttpServletRequest request,
    Exception e) {
    LOGGER.error(e.getMessage(), e);
    return RestResult.invalidParams(e.getMessage());
  }

  /**
   * 处理服务器端业务异常
   *
   * @param request 请求对象
   * @param e       异常对象
   * @return
   */
  @ExceptionHandler(BizException.class)
  @ResponseBody
  public RestResult<String> handleBusinessException(HttpServletRequest request, BizException e) {
    LOGGER.error(e.getMessage(), e);
    return RestResult.fail(e.getMessage());
  }

  /**
   * 注册全局数据编辑器
   *
   * @param binder  数据绑定
   * @param request web请求
   */
  @InitBinder
  public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }
}
