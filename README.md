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
- 部署方式：Jenkins + Rancher

## 认证及权限
### 相关文件及作用


## 日志记录
1. 配置文件：resources/logback-spring.xml
2. 日志记录方式：可选 数据库 和 文件 方式
3. 数据库方式
   > - 数据库表为 logging_event
   > - 实体为 site.fish.entity.log.Logging_event
4. 文件方式
   > - 路径为项目根目录下log目录
   > - docker方式部署时（含Rancher），建议将log目录映射到宿主机
   
