#!/bin/bash
source /etc/profile
echo "stop storm server..."
kill -9 `ps -ef|grep daemon.nimbus | awk '{print $2}'|head -1` >${STORM_HOME}/nimbus.log 2>&1
kill -9 `ps -ef|grep core | awk '{print $2}'|head -1` >${STORM_HOME}/ui.log 2>&1
hosts="node1 node2 node3"
for host in $hosts
do
  echo "--------$host--------"
  ssh $host "source /etc/profile; /home/hadoop/shelltools/stop-supervisor.sh >${STORM_HOME}/supervisor.log 2>&1 &"
  echo "OK!"
done