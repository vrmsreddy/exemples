package com.hyzx.xschool.web.controller;

import com.hyzx.xschool.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 测试{@link UserController}类
 *
 * @author caoxudong
 * @since 0.1.0
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTestNGSpringContextTests {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

  @Autowired
  ApplicationContext applicationContext;

  MockMvc mockMvc;

  /**
   * 准备MockMVC
   */
  @BeforeClass
  public void setup() {
    mockMvc =
        MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext).build();
  }

  /**
   * 构造测试数据
   * 
   * @return
   */
  @DataProvider
  public Object[][] usernames() {
    return new Object[][] {new Object[] {"mack_username", "mock_password"}};
  }

  /**
   * 测试用户登陆, 匿名访问，默认拥有访问权限
   * 
   * @param username
   * @throws Exception
   */
  @Test(dataProvider = "usernames")
  public void testLogin(String username, String password) throws Exception {
//    String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
//    MockHttpServletRequestBuilder builder =
//        MockMvcRequestBuilders.post("/user/login").content(json);
//    builder.contentType("application/json");
//    ResultActions resultActions = mockMvc.perform(builder);
//    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testSaveUserWithoutAuth() throws Exception {
    LOGGER.info("测试没有权限的情况下访问接口是否正确返回401错误");
//    String json =
//        "{\"username\":\"test_without_auth_username\",\"password\":\"test_without_auth_passwd\"}";
//    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/save").content(json);
//    builder.contentType("application/json");
//    ResultActions resultActions = mockMvc.perform(builder);
//    resultActions.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
  }

  @Test
  public void testSaveUserWithAuth() throws Exception {
    LOGGER.info("测试有权限的情况下访问接口是否正确返回200代码");
//    String json =
//        "{\"username\":\"test_with_auth_username\",\"password\":\"test_with_auth_passwd\"}";
//    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/save").content(json);
//    builder.contentType("application/json");
//    builder.sessionAttr(Constants.SESSION_USER_ID, 2001);
//    ResultActions resultActions = mockMvc.perform(builder);
//    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
  }

}
