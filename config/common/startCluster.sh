kafka eagle
http://hadoop-103:8048/ke/
Account:admin ,Password:123456

storm
http://hadoop-103:8081/index.html

zk_ui
http://hadoop-103:9091/home
username: admin, pwd: manager


xcall  /home/zookeeper/bin/zkServer.sh  start
xcall  /home/zookeeper/bin/zkServer.sh  status
/home/zookeeper/zk_ui/zk_ui_start.sh


xcall  /home/kafka/kafka/bin/kafka-server-start.sh -daemon /home/kafka/kafka/config/server.properties
xcall jps | grep 'Kafka'
/home/kafka/eagle/bin/ke.sh  start


/home/clusterBin/start_storm.sh
xcall /home/clusterBin/check_storm_status.sh
xcall /home/clusterBin/stop_storm.sh


xcall ls /var/run/redis_6379.pid
xcall rm -rf /var/run/redis_6379.pid
xcall /etc/init.d/redis start
xcall /etc/init.d/redis stop
xcall /home/redis/bin/redis-sentinel /home/redis/redis-6.2.1/sentinel.conf

xcall  /home/elk/elasticsearch-7.13.4/start_es.sh
xcall  /home/elk/elasticsearch-7.13.4/stop_es.sh
xcall ps -ef|grep elasticsearch |grep -v grep
xcall ps -ef|grep elasticsearch |grep -v grep

!!!!关注中间件开源web可视化工具。并贡献代码