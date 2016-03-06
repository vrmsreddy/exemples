package com.hyzx.xschool.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC 的辅助配置, 用来注册拦截器.
 */
@Configuration public class DefaultWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

  @Autowired
  ApplicationContext applicationContext;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    AccessControlInterceptor aci = applicationContext.getBean(AccessControlInterceptor.class);
    registry.addInterceptor(aci);

    //TODO  此处配置excludePathPatterns不生效
    AccessControlInterceptor accessAuthTokenValidationInterceptor =
        applicationContext.getBean(AccessControlInterceptor.class);
    registry.addInterceptor(accessAuthTokenValidationInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/html/login.html", "/login", "/logout");

    registry.addInterceptor(applicationContext.getBean(AuthorityControlInterceptor.class))
        .addPathPatterns("/**")
        .excludePathPatterns(
          "/organization/list",
          "/organization/*",
          "/organization/save",
          "/organization/*/update/*",
          "/organization/*/pic/list",
          "/organization/pic/remove",
          "/organization/pic/save",
          "/organization/valid/list",
          "/organization/course/**",
          "/logout",
          "/login");
  }

}
