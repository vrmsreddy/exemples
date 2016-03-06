package com.hyzx.xschool.util.acs;


/**
 * 
 * @author qly
 *
 */
public enum AcsOperation {
  NULL(null, null, 0),
  // 用户模块
  USER_LIST("用户模块", "获取用户列表", 100),
  //
  ;

  // 模块
  private String module;
  // 操作
  private String operation;
  // 级别
  private int level;

  AcsOperation(String module, String operation, int level) {
    this.module = module;
    this.operation = operation;
    this.level = level;
  }

  public String getModule() {
    return module;
  }

  public String getOperation() {
    return operation;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public String toString() {
    return this.module + " > " + this.operation + " > " + this.level;
  }

}
