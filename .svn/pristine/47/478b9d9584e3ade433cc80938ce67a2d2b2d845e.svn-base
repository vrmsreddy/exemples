package com.hyzx.xschool;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import io.swagger.annotations.Api;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger1.annotations.EnableSwagger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.hyzx.xschool.config.AppConfig;

import com.fasterxml.classmate.TypeResolver;

import java.util.List;

/**
 * Spring Boot入口主类
 * <p/>
 * 使用了Swager进行API生成
 *
 * @author royguo@uworks.cc
 * @see https://github.com/springfox/springfox-demos/tree/master/boot-swagger
 * @since 0.1.0
 */
@SpringBootApplication
@EnableSwagger
@EnableSwagger2
public class App {

  @Autowired
  AppConfig appConfig;

  /**
   * 应用程序入口
   *
   * @param args
   */
  public static void main(String[] args) {
    // 启动容器.
    SpringApplication.run(App.class, args);
  }

  /**
   * 生成API文档的入口.
   */
  @Bean
  public Docket generateApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
        // 标示只有被 @Api 标注的才能生成API.
      .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
      .paths(PathSelectors.any())
      .build()
      .pathMapping("/")
      .directModelSubstitute(LocalDate.class, String.class)
      .genericModelSubstitutes(ResponseEntity.class)
      .alternateTypeRules(
        newRule(
          typeResolver.resolve(DeferredResult.class,
            typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
          typeResolver.resolve(WildcardType.class)))
      .useDefaultResponseMessages(false)
      .globalResponseMessage(
        RequestMethod.GET,
        newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
          .responseModel(new ModelRef("Error")).build()))
      .securitySchemes(newArrayList(apiKey())).securityContexts(newArrayList(securityContext()));
  }

  @Autowired
  private TypeResolver typeResolver;

  private ApiKey apiKey() {
    return new ApiKey("mykey", "api_key", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth())
      .forPaths(PathSelectors.regex("/anyPath.*")).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return newArrayList(new SecurityReference("mykey", authorizationScopes));
  }

  @Bean
  SecurityConfiguration security() {
    return new SecurityConfiguration("test-app-client-id", "test-app-realm", "test-app", "apiKey");
  }

  @Bean
  UiConfiguration uiConfig() {
    return new UiConfiguration("validatorUrl");
  }

}
