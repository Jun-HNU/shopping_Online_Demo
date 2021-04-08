
<dependency>
<groupId>org.mybatis.spring.boot</groupId>
<artifactId>mybatis-spring-boot-starter</artifactId>
<!-- 可能存在版本不匹配-->
<version>1.3.1</version>
</dependency>



看到121集  92-94
kafka看到p27,17分:https://www.bilibili.com/video/BV1a4411B7V9?p=26&spm_id_from=pageDriver
kafka看到p6 https://www.bilibili.com/video/BV1oK4y1x75C?p=6&spm_id_from=pageDriver
hadoop看到p57 https://www.bilibili.com/video/BV1Qp4y1n7EN?p=49&spm_id_from=pageDriver
zookeeper p05https://www.bilibili.com/video/BV1M741137qY?p=5&spm_id_from=pageDriver
ES https://www.bilibili.com/video/BV1Jh41117bi?p=16&spm_id_from=pageDriver


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

服务器需要修改的的ip地址
vim /home/elk/elasticsearch-6.3.1/config/elasticsearch.ym
network.host:192.168.5.138

vim /home/elk/kibana-6.3.1-linux-x86_64/config
elasticsearch.url -->192.168.5.138

为elk启动，特意创建了elkuser这个普通用户
必须先启动 elasticsearch  再启动kibana
elk必须采用非root用户启动
su elkuser切换用户
nohup /home/elk/elasticsearch-6.3.1/bin/elasticsearch &
nohup /home/elk/kibana-6.3.1-linux-x86_64/bin/kibana  &


elasticsearch 远程访问：192.168.5.138:9200

kibana 远程访问：192.168.5.138:5601

maven
service network   restart

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