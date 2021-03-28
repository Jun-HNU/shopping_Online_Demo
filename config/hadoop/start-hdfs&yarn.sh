
ssh hadoop-101 /home/hadoop/hadoop/sbin/stop-dfs.sh
ssh hadoop-101 /home/hadoop/hadoop/sbin/start-dfs.sh


#yarn必须在ResourceManager节点上启动
ssh hadoop-102 /home/hadoop/hadoop/sbin/start-yarn.sh

ssh hadoop-102 /home/hadoop/hadoop/sbin/stop-yarn.sh

#historyserver安装在hadoop-101上，必须在hadoop-101上启动
ssh hadoop-101 /home/hadoop/hadoop/bin/mapred --daemon start historyserver