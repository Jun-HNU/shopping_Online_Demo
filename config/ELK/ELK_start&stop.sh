为elk启动，特意创建了elkuser这个普通用户
必须先启动 elasticsearch  再启动kibana
elk必须采用非root用户启动
su elkuser切换用户
nohup /home/elk/elasticsearch-6.3.1/bin/elasticsearch &
nohup /home/elk/kibana-6.3.1-linux-x86_64/bin/kibana  &


/home/elk/elasticsearch-6.3.1/start_es.sh
/home/elk/elasticsearch-6.3.1/stop_es.sh