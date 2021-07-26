#!/bin/bash
kill -9 `ps -ef|grep elasticsearch |grep -v grep| awk '{print $2}'|head -1`

# xcall  /home/elk/elasticsearch-7.13.4/start_es.sh
# xcall  /home/elk/elasticsearch-7.13.4/stop_es.sh
# xcall ps -ef|grep elasticsearch |grep -v grep