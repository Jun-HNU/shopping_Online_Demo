
<dependency>
<groupId>org.mybatis.spring.boot</groupId>
<artifactId>mybatis-spring-boot-starter</artifactId>
<!-- 可能存在版本不匹配-->
<version>1.3.1</version>
</dependency>



看到118集  92-94
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


服务器需要修改的的ip地址
vim /home/elk/elasticsearch-6.3.1/config/elasticsearch.ym
network.host:192.168.5.138

vim /home/elk/kibana-6.3.1-linux-x86_64/config
elasticsearch.url -->192.168.5.138

为elk启动，特意创建了elkuser这个普通用户
su elkuser切换用户
elasticsearch 远程访问：192.168.5.138:9200

kibana 远程访问：192.168.5.138:5601





