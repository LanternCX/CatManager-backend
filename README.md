# 厦一海沧 | 流浪猫管理系统 服务后端

厦门一中海沧校区-校园流浪猫管理系统（2023厦门市学生信息素养提升实践活动参赛作品）

后端方案使用[Spring Boot](https://spring.io/)配合[Mysql](https://www.mysql.com/)搭建

Web前端：[CatManager-frontend](https://github.com/CDBxinhe/CatManager-frontend)

特别鸣谢：[XBigRiceH (Hang XU)](https://github.com/XBigRiceH)

## Quick Start
### 环境要求
* Java **JDK** >= 17.0
* Maven
* 一个比较好的网络用以下载项目依赖
* MySql >= 5.7

### 编译操作
* 使用```mvn package -Dmaven.test.skip=true```来**跳过测试**~~(虽然打包上交的项目里也没有)~~并打包最终产物

### 运行
* 编辑**源码压缩包**内的**application.properties**配置文件，并将其与打包产物放在**同一个目录下**
* 使用`java -jar xylive-0.0.1-SNAPSHOT.jar`指令来启动项目，也可以使用`-Xmx`或`-Xms`参数来指定最大/最小内存

### 配置文件
```properties
# ==========Spring基础配置==========
# 后端HTTP监听端口
server.port=60001
# 数据库用户名
# 如 cat
spring.datasource.username=
# 数据库密码
# 如 catcat
spring.datasource.password=
# 数据库URL
# 示例(MYSQL): jdbc:mysql://[数据库地址]:[数据库端口]/[数据库名]?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
# 如 jdbc:mysql://localhost:3306/cat?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.url=
# 示例配置中配置了一个运行在本地的MySql服务,使用用户名cat密码catcat,连接到了cat数据库
# Windows部署MySql环境可使用小皮面板https://www.xp.cn/,CentOS环境建议使用宝塔面板https://www.bt.cn/
# ==========业务基础配置==========
# 默认海报地址
cat.default-poster=https://xuebuxi-imgci.xyget.cn/in1z/542f447a16c95e82ba4f9772bd037656.jpg
# 默认海报路径
cat.image-root=
# ==========Log4j基础配置-一般无需更改==========
# 主日志等级
logging.level.root=info
# WEB日志等级
logging.level.web=error
# SQL日志等级
logging.level.sql=fatal
# 自身日志等级
logging.level.tech.xysu.xyhc.xybackend.*=info
# 日志文件名
logging.file.name=logs/cat_logs.log
# ==========其他配置-请勿修改==========
spring.jpa.open-in-view=false
spring.jpa.hibernate.ddl-auto=update
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=100MB
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
```

#### HTTPS
本项目内没有内置HTTPS设置，请自行设置**反向代理**， 例如使用**nginx**的反向代理功能

#### 退出
请使用**Ctrl+C**并等待其自动终止进程，因为在项目退出前还有一些操作需要执行