package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.domain.User;
import com.hyzx.xschool.domain.enums.UserType;
import com.hyzx.xschool.domain.repository.UserRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.UserService;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.PageQueryFormBean;
import com.hyzx.xschool.web.controller.request.UserQueryFormBean;
import com.hyzx.xschool.web.controller.response.OrganizationInfo;
import com.hyzx.xschool.web.controller.response.UserTrainPlanInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理
 *
 * @author caoxudong
 * @since 0.1.10
 */
@Api(value = "用户", produces = "application/json")
@RestController
@RequestMapping("/user")
public class UserController {
  private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  /**
   * 查看用户列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表", notes = "分页获取用户数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<User> list(@RequestBody UserQueryFormBean q) {
    LOGGER.info("查看用户 -> {}", q.toString());

    Page<User> page = userService.findByPage(q);
    return RestResult.ok(page);
  }

  /**
   * 查看用户.
   *
   * @param userId
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表：查看用户", notes = "查看用户详情")
  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public RestResult<User> detail(@PathVariable("userId") Long userId) {
    User user = userService.findById(userId);
    return RestResult.ok(user);
  }

  /**
   * 查看用户收藏的机构.
   *
   * @param userId
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表：查看用户收藏的机构", notes = "查看用户收藏的机构列表")
  @RequestMapping(value = "/{userId}/favoriteOrgans", method = RequestMethod.GET)
  public RestResult<OrganizationInfo> listFavoriteOrgan(@PathVariable("userId") Long userId) {
    List<OrganizationInfo> list = userService.findFavoriteOranizations(userId);
    return RestResult.ok(list);
  }


  /**
   * 重置密码.
   *
   * @param userId
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表：重置密码", notes = "发送短信验证码")
  @RequestMapping(value = "/{userId}/resetPass", method = RequestMethod.POST)
  public RestResult<Void> resetPassword(@PathVariable("userId") Long userId) {
    userService.resetPassword(userId);
    return RestResult.ok();
  }

  /**
   * 更新状态：启用/关闭.
   *
   * @param userId
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表：启用/禁用", notes = "更新用户状态")
  @RequestMapping(value = "/{userId}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("userId") Long userId, @PathVariable("status")
  int status) {

    User user = userRepository.findOne(userId);
    if (user == null) {
      throw new BizException("用户不存在");
    }

    user.setStatus(status);
    userRepository.save(user);
    return RestResult.ok();
  }

  /**
   * 更新用户类型：外部人员、内部员工.
   *
   * @param userId
   * @return
   */
  @ApiOperation(value = "用户管理-用户列表：启用/禁用", notes = "更新用户状态")
  @RequestMapping(value = "/{userId}/type/{userType}", method = RequestMethod.POST)
  public RestResult<Void> updateAdminType(@PathVariable("userId") Long userId,
    @PathVariable("userType") int userType) {

    User user = userRepository.findOne(userId);
    if (user == null) {
      throw new BizException("用户不存在");
    }
    if (user.getType() != UserType.NORMAL.getCode() && user.getType() != UserType.OUTSIDE
      .getCode()) {
      throw new BizException("该用户不是管理员");
    }

    user.setType(userType);
    userRepository.save(user);
    return RestResult.ok();
  }

  /**
   * 查看培养记录列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "用户管理-培养状态统计", notes = "分页获取培养记录")
  @RequestMapping(value = "/trainPlan/list", method = RequestMethod.POST)
  public RestResult<UserTrainPlanInfo> listUserTrainPlans(@Valid @RequestBody PageQueryFormBean q) {
    LOGGER.info("查看培养记录 -> {}");

    Pageable pageable =
        new PageRequest(q.getPageNo() - 1 , q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
    Page<UserTrainPlanInfo> page = userService.findTrainPlanByPage(pageable);
    return RestResult.ok(page);
  }
}
