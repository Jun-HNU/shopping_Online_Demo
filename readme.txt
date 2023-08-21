
<dependency>
<groupId>org.mybatis.spring.boot</groupId>
<artifactId>mybatis-spring-boot-starter</artifactId>
<!-- 可能存在版本不匹配-->
<version>1.3.1</version>
</dependency>



看到124集 ELK集群的搭建 https://www.bilibili.com/video/BV1B4411V7cA?p=124
92-94
182
kafka看到p27,17分:https://www.bilibili.com/video/BV1a4411B7V9?p=26&spm_id_from=pageDriver
kafka看到p6 https://www.bilibili.com/video/BV1oK4y1x75C?p=6&spm_id_from=pageDriver
hadoop看到p57 https://www.bilibili.com/video/BV1Qp4y1n7EN?p=49&spm_id_from=pageDriver
zookeeper p05https://www.bilibili.com/video/BV1M741137qY?p=5&spm_id_from=pageDriver
ES https://www.bilibili.com/video/BV1Jh41117bi?p=16&spm_id_from=pageDriver
IO框架p10 https://www.bilibili.com/video/BV1Tz4y1X7H7?p=10&spm_id_from=pageDriver
rpc: 24 https://www.bilibili.com/video/BV17Z4y1s7cG?from=search&seid=15445265319622836914
谷粒商城2020https://www.bilibili.com/video/BV1np4y1C7Yf?p=3&spm_id_from=pageDriver
servlet教程:https://www.bilibili.com/video/BV1y5411p7kb?from=search&seid=14342678742130979573

redis p13：https://www.bilibili.com/video/BV1S54y1R7SB?p=13&spm_id_from=pageDriver
https://www.bilibili.com/video/BV1Tp4y187Jj?from=search&seid=6823215720785951804
#42-51m没看
# 在gmail-admin目录下运行 npm run dev
localhost:8080/swagger-ui.html
http://192.168.5.138:8080/dubbo-admin-2.6.0/
可以看到要提示用户名密码，默认是root/root
/home/zookeeper/bin/zkServer.sh restart

连接redis:redis-cli -h 192.168.5.138 -p 6379
hadoop-103为服务器
elk curl 127.0.0.1:9200

前端的网址为
http://localhost:8888/

# gmall0105 本地修改版本
# gmall-user用户服务8080
gmall-user-service用户服务的service层8070
gmall-user-web用户服务的web层8080

gmall-manage-service用户服务的service层8071
gmall-manage-web用户服务的web层8081

# gmall-item-service前台的商品详情服务 8072
gmall-item-web前台的商品详情展示 8082

shopping-redisson-test前台的商品详情展示 8888

gmall-search-web 搜索服务的前台 8083
gmall-search-service 搜索服务的后台 8073


gmall-cart-web 搜索服务的前台 8084
gmall-cart-service 搜索服务的后台 8074

gmall-passport-web 用户认证中心 8085
gmall-user-service 用户服务的service层8070

gmall-order-web 订单 8086
gmall-order-service 订单服务 8076

gmall-payment 8087

vim /etc/sysconfig/network-scripts/ifcfg-eth0
添加
BOOTPROTO="static"
IPADDR=192***** （改为自己想要固定的IP即可）



启动zooke ：/home/zookeeper/bin/zkServer.sh restart
连接zookeeper
搭建zookeeper+kafka集群，及其可视化工具
https://www.cnblogs.com/panwenbin-logs/p/10369402.html


dos命令行
D:\IDEA&springboot\mySSMproject\ZooInspector\build>
> java -jar zookeeper-dev-ZooInspector.jar

192.168.5.138:2181,192.168.5.131:2181,192.168.5.132:2181

hadoop 看到25
https://www.bilibili.com/video/BV1Qp4y1n7EN?p=25


.DER .CER，文件是二进制格式，只保存证书，不保存私钥。
.PEM，一般是文本格式，可保存证书，可保存私钥。
.CRT，可以是二进制格式，可以是文本格式，与 .DER 格式相同，不保存私钥。
.PFX .P12，二进制格式，同时包含证书和私钥，一般有密码保护。
.JKS，二进制格式，同时包含证书和私钥，一般有密码保护。

GitHub-Chinese-Top-Charts
————————————————
Restful Spring/SpringCloud  Mybatis Mysql  Kafka  Redis  ElasticSearch 
具备一定的计算机理论基础，理解Linux IO/NIO、文件系统；理解JVM、Mysql、Redis和Kafka底层基本原理，熟悉Maven, Spring的使用，会SpringCloud相关组件和Docker基本使用。

1)自定义注解和token策略实现接口幂等，Javassist 动态修改第三方jar包中的类。

2)设置JVM启动时各项参数；运用JVM工具命令调整运行时各项参数或查看运行时不同指标，结合Linux命令查看占用空间较大的java 线程栈上下文；ELK抓取分析GC log中的关键词；通过分析JVM dump 定位频繁full GC等问题。

3)建立合适的索引、视图、存储过程，运用表的水平分割，设计读写分离逻辑提高查询效率；定位慢查询问题、索引失效问题。

4)自定义注解，拦截函数调用，在Redis中缓存热点数据降低数据库访问压力； Redis 作为分布式锁，解决分布式环境下同步资源读写问题；运用适当策略避免Redis缓存击穿,穿透,雪崩的情形。

5)运用Kafka削峰和解耦提高系统稳定性；运用kafka 实现不同客户端间缓存同步；解决kafka出现过的少消费、重复消费问题；运用拦截器解决kafka顺序性消费问题。

6)定制Kafka，Redis，ELK集群配置，指导协助技术支持工程师运维；

7)主导完成部分项目架构设计，包括maven项目构建、打包，相关接口和表设计，项目部署与维护。

	2019.07-----至今	Citi集团 (花旗金融信息服务（中国）有限公司)
	1.Quantum Product Service(产品业务近实时服务系统）：
1）	业务数据获取、转换、处理与落库设计：通过WebSocket/JMS/Restful API通道获取上游数据作为Storm spout数据源，运用Storm bolt进行实时处理，对于多个处理速度不一致的bolt采用Kafka进行削峰，最终不同产品数据经Storm bolt处理完后被持久化到数据库，同时推送一个拷贝到Kafka topic(A 类topic）。

2）	服务端设计: 服务端缓存的形式采用Redis集群或JVM本地缓存。服务端启动时先与数据库和Kafka建立连接，并读数据库中数据进行服务端缓存初始化，在初始化完后，服务端开始消费Kafka topic(A 类topic)近实时数据，或业务处理后的数据推送Kafka topic(B 类topic)，同时更新服务端缓存。服务端实时响应用户请求。被请求的数据在服务端缓存中不存在时，服务端将请求数据库，并更新服务端缓存。
            
3）	API设计：API基于JVM本地缓存。API初始化时，向服务端注册自
己，并发送心跳，以及向服务端请求与自身ID对应的配置信息并缓存在本地，根据相应配 置信息，建立与Kafka的连接，再依据其他配置信息，将特定的初始化业务数据缓存在本地，在初始化完后，客户端A类和B类topic数据被用于更新本地缓存。用户可通过服务端UI界面查看自身本地缓存的数据。
用户导入API maven坐标依赖后，可直接调用接口发送请求到服务端或调用接口获取
自身本地缓存的近实时数据。服务端和客户端API逻辑对于用户来说是透明的，用户调用本地口获取目标数据时，若本地缓存不存在目标数据，客户端API自动触发对服务端的请求。 

	2. CRDS-Service(交易类参考数据服务系统)：
为不同类型用户提供获取我们数据库数据的Web UI和REST 接口，用户通过Web界面和REST API更新不同类型交易参考数据时，服务端实现相应的业务计算，更新当前地区数据库和缓存，推送 Kafka topic用于更新其地区API缓存中的数据。

	3 . 开源Kafka-eagle/Logstash 二次开发：
Kafka-eagle 源码二次开发：监控zookeeper的状态，实现对不同zookeeper节点数据读写，监控多个kafka集群的broker，topic，partition，消费堆积等各项指标。实现kafka topic,partition的读写功能，实现不同用户读写权限控制，整合sso统一登陆，Kafka-eagle数据持久化。
Java版logstash 源码二次开发：替换ElasticSearch的transportClient API相关逻辑为使用restHighLevelClient API实现， java实现灵活高效的过滤器，可本地调试配置logstash过滤器的配置，提高了开发效率。

	4.CRDS-Tool：
基于web的应用：提供对ElasticSearch数据可视化更改与检索，业务相关指标的可视化展示与报表生成；QA环境 ,业务逻辑不同阶段半自动化测试，生产环境，上线新特性后的半自动化逻辑验证；数据库建新表或旧表加新加字段时，对应表或字段的源数据批量拉取，转换，解析与表数据初始化等。

