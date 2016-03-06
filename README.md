X-School Project
==============================

使用Spring-Boot搭建后端服务，启动一个新的项目直接从这个项目复制过去修改即可.

### 1.使用说明
1. 下载项目
2. 修改pom.xml中的关键数据到，变更为自己要开发的项目的名称
3. 使用`mvn spring-boot:run`尝试启动
4. 启动后访问`http://127.0.0.1:7080/swagger-ui.html`查看自动生成的API文档
5. 使用`mvn test`运行测试用例

### 2.数据库说明
默认情况下，为了方便直接启动，`src/main/resource`下的`application.yml`中的数据库配置是H2 Database, 需要手动修改为自己用到的数据库，同时把`pom.xml`中的H2依赖范围改为`test`.

### 3.权限控制模块说明
系统默认集成了一个设计好的权限控制模块(ACS)，但是需要各个系统独立的进行二次扩展支持.

#### 3.1.注解使用

```
	@ACS(allowAnonymous = true) : 表示允许匿名访问
	@ACS(operations = {Operation.User.编辑用户, Operation.User.创建用户})	: 表示只允许编辑用户或创建用户的操作访问
```

#### 3.2.权限配置
- 需要开发者在 `util.acs.Operation` 中预先定义本项目中所要使用的所有操作
- 这些操作会被系统初始化到数据库中的 `t_acs_operation` 表(见`domain.ACSOperation`类)
- 开发者需要在运营系统中增加一个功能用来配置角色拥有哪些具体的`操作(即Operation)`, 进一步配置用户拥有哪些角色

#### 3.3.测试用例
请参看 `src/test/java/`下的`cc.uworks.example.web.controller.UserControllerTest.java`了解权限的测试用例及其含义.

#### 3.4. 权限模块的设计
- 初期为了简单起见,每个用户只拥有一个单一的角色
- 每个角色拥有一组`操作`, 即`ACSOperation`, 这些操作是在系统初始化的时候自动添加到数据库的
- 开发者需要自己给角色配置操作, 这些配置是一次性的
- 每个操作需要哪些具体的`Action`是通过`@ACL`注解在`Controller`层配置的


### 4.贡献代码
大家贡献代码的时候，在自己本地切换出来新的分支，以`feature name`命名，推送到项目后提交`merge request`即可
