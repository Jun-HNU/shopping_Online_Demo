
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

