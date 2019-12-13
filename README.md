编辑于2019/12/13

# Spring + SpringMVC + Mybaties模板框架



## Spring版本为 4.2.5.RELEASE



## 框架布局

* framework

  ~~~properties
  存放项目中的基础配置，如：
  Controller父类，Entity父类
  ResultView: 项目默认返回实体类
  
  aop:切面配置类
  security:拦截器，过滤器
  util:工具类
  ~~~

* entity

  ~~~properties
  存放整个项目中的各个实体类
  
  constan:静态常量
  po:数据库字段映射
  vo:前后端传输
  dto:服务之间传输
  ~~~

* dao

  ~~~properties
  数据访问层
  ~~~

  * mapper

* service

  ~~~properties
  业务层
  ~~~

  * serivceImpl

* web

  * controller

    ~~~properties
    表现层
    ~~~

  * timer

    ~~~properties
    定时任务
    ~~~



### 日志 

log4j2

~~~properties
更能最强大同时效率也最好的记录日志的工具
~~~

### 数据库连接池

druid

### 缓存

redis

~~~properties
getLock()方法可以使用redis分布式锁
~~~

### 消息队列

rabbitMQ

~~~properties
工具类自己封装的
~~~

### JSON

fastjson

~~~properties
阿里出品的效率较好的JSON工具
缺点是使用fastjson将实体类转换为JSON时，value为null的数据直接就不要了
~~~

### 邮件

easyexcel

### 加解密

AES，Base64，MD5