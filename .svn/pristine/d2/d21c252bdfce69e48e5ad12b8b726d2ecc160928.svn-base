package com.hyzx.xschool.util.acs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author qly
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ACS {
  /**
   * 操作名称，多个action操作组合称为一个operation.
   * <p>
   * 示例： 用户模块_编辑用户_10
   */
  AcsOperation[]operations() default AcsOperation.NULL;

  /**
   * 是否允许匿名访问, 如果允许则会忽略上面的operations设置.
   */
  boolean allowAnonymous() default false;
}
