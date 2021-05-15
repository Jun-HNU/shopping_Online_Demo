#!/bin/sh
. /etc/profile
. ~/.bash_profile


#必须先启动zookeeper才能启动kafka
date=`date`
cd /usr/local/kafka
echo "$date star to monitor kafka" >> /home/kafka/kafka/monitor.log

pid=`jps | grep 'Kafka'`
if [ -z "$pid" ];then
    echo "find kafka down" >> /home/kafka/kafka/monitor.log
    xcall  /home/kafka/kafka/bin/kafka-server-start.sh -daemon /home/kafka/kafka/config/server.properties
    sleep 2
fi

#xcall  /home/kafka/kafka/bin/kafka-server-start.sh /home/kafka/kafka/config/server.properties
##xcall  /home/kafka/kafka/bin/kafka-server-start.sh -daemon /home/kafka/kafka/onfig/server.properties

xcall jps | grep 'Kafka'