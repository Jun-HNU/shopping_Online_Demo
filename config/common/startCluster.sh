xcall  /home/zookeeper/bin/zkServer.sh  start
xcall  /home/zookeeper/bin/zkServer.sh  status
http://hadoop-103:8048/ke/
http://hadoop-103:8081/index.html

xcall  /home/kafka/kafka/bin/kafka-server-start.sh -daemon /home/kafka/kafka/config/server.properties
xcall jps | grep 'Kafka'
/home/kafka/eagle/bin/ke.sh  start


/home/clusterBin/start_storm.sh
xcall /home/clusterBin/check_storm_status.sh
