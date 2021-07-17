#!/bin/bash
echo "start storm server..."
source /etc/profile
#前面配置过环境变量，/home/storm/apache-storm-1.2.3/bin目录下的脚本就可以在任意目录下使用。

#前面配置过"hadoop-103"和"hadoop-101"为主节点, 在实际中只有一个主节点，其他的就是备用节点。对于"hadoop-103"和"hadoop-101"为 启动如下服务

master_hosts="hadoop-101 hadoop-102 hadoop-103"
for host in $hosts
  do
    echo "--------$host--------"
    echo "*********satrt nimbus master*********"
    storm nimbus >${STORM_HOME}/nimbus.log 2>&1 &
    echo "*********satrt nimbus UI*********"
    storm ui >${STORM_HOME}/ui.log 2>&1 &
    echo "OK!"
  done




#"hadoop-103","hadoop-101" 和“hadoop-002” 启动 supervisor 服务和 logviewer 服务：

master_hosts="hadoop-101 hadoop-102 hadoop-103"
for host in $hosts
  do
    echo "--------$host--------"
    echo "*********satrt nimbus supervisor*********"
    ssh $host  "source /etc/profile; ${STORM_HOME}/bin/storm supervisor >${STORM_HOME}/supervisor.log 2>&1 &"
    echo "*********satrt nimbus logviewer*********"
    ssh $host  "source /etc/profile; ${STORM_HOME}/bin/storm logviewer >${STORM_HOME}/logviewer.log 2>&1 &"
    echo "OK!"
  done
