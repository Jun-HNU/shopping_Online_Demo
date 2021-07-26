#!/bin/bash
su elkuser -c "sh /home/elk/elasticsearch-7.13.4/bin/elasticsearch -d -p /home/elk/elasticsearch-7.13.4/pid" > /home/elk/elasticsearch-7.13.4/logs/startESCluster.log  2>&1
# xcall  /home/elk/elasticsearch-7.13.4/start_es.sh
# xcall  /home/elk/elasticsearch-7.13.4/stop_es.sh
# xcall ps -ef|grep elasticsearch |grep -v grep