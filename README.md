# Single Module Scaffold

Single Module Scaffold project for Spring Boot

Java后端单模块脚手架工程

## 技术栈
- 基础：spring boot
- 持久层：spring data jpa
- 数据库：mysql
- 数据库版本管理：flyway
- 连接池：hikari
- 认证与权限：spring security
- Token：jwt
- 其他：lombok
- JsonApi：swagger

## 认证及权限
### 相关文件及作用
![Spring Security Architecture](https://raw.githubusercontent.com/fish119/single-module-scaffold/master/springsecurity.webp)
- 相关类均在已下包中：site.fish.security
- site.fish.security.WebSecurityConfig：Spring Security 配置类
- site.fish.security.AccessDecisionManagerImpl：权限验证访问决策管理器
- site.fish.security.AuthenticationTokenFilter：认证Filter
- site.fish.security.Constant：相关常量
- site.fish.security.JwtTokenUtil：JwtToken相关工具类
- site.fish.security.UserDetailsServiceImpl：自定义UserDetailsService实现类
- site.fish.security.AuthorityCache：临时使用。首次访问后将数据库中的Authority缓存至内存

### 初始化数据
- 初始化用户
  > 用户名：admin 、 test
  > 
  > 密码：password
  > 
  > 前端需传入md5加密后的密码，即 5f4dcc3b5aa765d61d8327deb882cf99
  > 
  > 数据库中密码存储为：
  > > 推荐/系统使用：{bcrypt}$2a$10$mDaccojixbnAyaF/wVzIE.ik4LXKPfQzlKFJyye4ByQuQddLhumU2
  > > 不推荐/未使用：{noop}21232f297a57a5a743894a0e4a801fc3
- 初始化角色
- 初始化权限

## 返回值
### 配置
- 异常返回格式：site.fish.config.CustomErrorAttributes
- 异常信息常量：site.fish.config.ExceptionMessage

### 正常返回值
- 使用 Spring 的 ResponseEntity，未作再次封装
- 仅当 HttpStatus 为 200 时返回正常结果
- 其他任何异常情况，均使用抛出异常的方式返回统一格式的异常结果

### 异常返回值
使用 Spring 默认的异常格式，如：
```json
{
    "timestamp": "2021-02-01 21:19:34",
    "status": 401,
    "error": "Unauthorized",
    "message": "认证失败，请登录后重试",
    "path": "/api/apiTest"
}
```

## 数据库版本管理
- 使用Flyway
- 相关配置文件目录：resources/db/
- 采用默认配置，未作修改

## Json Api文档
- 使用Swagger 3.0
- pom.xml仅需如下引用即可，无需引用swagger-core、swagger-ui
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```  
- 配置类：site.fish.config.SpringFoxConfig
- SwaggerUI访问地址：serverUrl:serverPort/swagger-ui

## 根据配置在生成API时将自动配置权限至数据库
按规范在Controller类中编写Swagger注释，启动项目时按配置自动将controller信息同步至数据库权限相关表。
详见：site.fish.config.CustomApiBuilder.java

application.yml中设置自定义参数
- 是否根据swagger更新权限及权限组数据表
create-authority-from-swagger: true
- 是否覆盖原有权限数据（清空原有，重新生成）
override-authority-from-swagger: false
- 是否自动将更新的权限赋予admin角色
auto-authorize-to-admin: true

## 日志记录
1. 配置文件：resources/logback-spring.xml
2. 日志记录方式：可选 数据库 和 文件 方式
3. 数据库方式
   > - 数据库表为 logging_event
   > - 实体为 site.fish.entity.log.LoggingEvent
4. 文件方式
   > - 路径为项目根目录下log目录
   > - docker方式部署时（含Rancher），建议将log目录映射到宿主机
   
