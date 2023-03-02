# 厦一学源 Backend
## Starting
### 环境要求
* Java **JDK** >= 17.0
* Maven
* 一个比较好的网络用以下载项目依赖

### 编译操作
* 使用```mvn package -Dmaven.test.skip=true```来**跳过测试**~~(虽然打包上交的项目里也没有)~~并打包最终产物

### 运行
* 编辑**源码压缩包**内的**application.properties**配置文件，并将其与打包产物放在**同一个目录下**
* 使用`java -jar xylive-0.0.1-SNAPSHOT.jar`指令来启动项目，也可以使用`-Xmx`或`-Xms`参数来指定最大/最小内存

### 配置文件
```properties
# ==========Spring基础配置==========
# 后端HTTP监听端口
server.port=
# 数据库用户名
spring.datasource.username=
# 数据库密码
spring.datasource.password=
# 数据库URL
# 示例(MYSQL): jdbc:mysql://[数据库地址]:[数据库端口]/[数据库名]?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.url=

# ==========其他配置-请勿修改==========
spring.jpa.open-in-view=false
spring.jpa.hibernate.ddl-auto=update
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=100MB
```

#### HTTPS
本项目内没有内置HTTPS设置，请自行设置**反向代理**， 例如使用**nginx**的反向代理功能

#### 退出
请使用**Ctrl+C**并等待其自动终止进程，因为在项目退出前还有一些操作需要执行