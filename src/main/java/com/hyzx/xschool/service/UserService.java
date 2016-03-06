package com.hyzx.xschool.service;

import com.hyzx.xschool.domain.User;
import com.hyzx.xschool.web.controller.request.UserPassFormBean;
import com.hyzx.xschool.web.controller.request.UserQueryFormBean;
import com.hyzx.xschool.web.controller.response.OrganizationInfo;
import com.hyzx.xschool.web.controller.response.UserTrainPlanInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 处理与用户相关的业务
 *
 * @author royguo
 * @since 0.1.0
 */
public interface UserService {
  User findById(Long id);

  /**
   * 重置用户密码并发送新密码到用户手机
   *
   * @param userId
   */
  void resetPassword(Long userId);

  Page<UserTrainPlanInfo> findTrainPlanByPage(Pageable pageable);

  User login(String username, String password);

  Page<User> findByPage(UserQueryFormBean q);

  void modifyPass(UserPassFormBean pass);

  List<OrganizationInfo> findFavoriteOranizations(Long userId);

  /**
   * 检查用户是否拥有某个操作的权限.
   * <p>
   * TODO 此部分可能需要改成从缓存获取数据，以便于更快速的查询.
   * 
   * @param userId
   * @param requiredOperations 当前操作需要什么样的权限，格式为 模块_操作名称_权限级别
   * @return true 如果用户至少拥有其中一个操作的权限.
   */
//  Boolean hasPermission(Long userId, Operation[] requiredOperations);
}
