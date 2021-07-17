#!/bin/bash
source /etc/profile
echo "stop storm server..."

master_hosts="hadoop-101 hadoop-103"
for host in $hosts
  do
    echo "--------$host--------"
    echo "*********stop nimbus master*********"
    ssh $host source /etc/profile; kill -9 `ps -ef|grep daemon.nimbus | awk '{print $2}'|head -1` >${STORM_HOME}/nimbus.log 2>&1
    echo "*********stop nimbus UI*********"
    ssh $host "source /etc/profile;" kill -9 `ps -ef|grep storm.ui.core | awk '{print $2}'|head -1` >${STORM_HOME}/ui.log 2>&1
    echo "OK!"
  done


slaves_hosts="hadoop-101 hadoop-102 hadoop-103"
for host in $hosts
  do
    echo "--------$host--------"
    echo "*********satrt nimbus supervisor*********"
    ssh $host "source /etc/profile;" kill -9 `ps -ef|grep daemon.supervisor | awk '{print $2}'|head -1` >${STORM_HOME}/supervisor.log 2>&1 &
    echo "*********satrt nimbus logviewer*********"
    ssh $host "source /etc/profile;" kill -9 `ps -ef|grep daemon.logviewer | awk '{print $2}'|head -1` >${STORM_HOME}/logviewer.log 2>&1 &
    echo "OK!"
  done


