package com.hyzx.xschool.web;

/**
 * Created by jack on 15-10-31.
 */
public class RestReturnCode {

  /**
   * 业务操作成功
   */
  public static final int SUCCESS = 200;

  /**
   * 业务操作失败
   */
  public static final int FAIL = 555;

  /**
   * 输入参数有误
   */
  public static final int INVALID_PARAM = 400;

  /**
   * 会话已过期
   */
  public static final int INVALID_SESSION = 403;

  /**
   * 系统异常
   */
  public static final int INNER_ERROR = 500;

}
