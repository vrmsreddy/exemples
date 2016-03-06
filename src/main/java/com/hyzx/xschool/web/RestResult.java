package com.hyzx.xschool.web;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 统一REST接口返回的数据格式为：{“code”:200,"msg":"sucess","result":[...]}
 * <p/>
 * Created by jack on 15-10-31.
 */
public class RestResult<T> {
  private int code;
  private String msg;
  private T result;

  /**
   * 数据记录总数：分页时使用
   */
  private int totalCount;

  public RestResult() {
  }

  public RestResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public RestResult(int code, String msg, T result) {
    this.code = code;
    this.msg = msg;

    this.result = result;
  }

  public RestResult(int code, String msg, T result, int totalCount) {
    this.code = code;
    this.msg = msg;
    this.result = result;
    this.totalCount = totalCount;
  }

  public static <T> RestResult ok() {
    return new RestResult(RestReturnCode.SUCCESS, "", null);
  }

  public static <T> RestResult ok(T result) {
    return new RestResult(RestReturnCode.SUCCESS, "", result);
  }

  public static <T> RestResult ok(Page<T> page) {
    return new RestResult(RestReturnCode.SUCCESS, "", page.getContent(),
        (int) page.getTotalElements());
  }

  public static <T> RestResult okPage(List<T> result, int totalCount) {
    return new RestResult(RestReturnCode.SUCCESS, "", result,
        totalCount);
  }

  public static <T> RestResult invalidParams(String msg) {
    return new RestResult(RestReturnCode.INVALID_PARAM, msg, null);
  }

  public static <T> RestResult fail(T result) {
    return new RestResult(RestReturnCode.FAIL, "", result);
  }
  public static <T> RestResult fail(String msg) {
    return new RestResult(RestReturnCode.FAIL, msg);
  }

  public static <T> RestResult error(String errMsg) {
    return new RestResult(RestReturnCode.INNER_ERROR, errMsg, null);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
