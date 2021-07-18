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
