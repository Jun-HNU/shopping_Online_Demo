#!/bin/bash
source /etc/profile
echo "stop storm server..."


echo "*********stop nimbus master*********"
kill -9 `ps -ef|grep daemon.nimbus |grep -v grep| awk '{print $2}'|head -1` >${STORM_HOME}/nimbus.log 2>&1
echo "*********stop nimbus UI*********"
kill -9 `ps -ef|grep storm.ui.core |grep -v grep| awk '{print $2}'|head -1` >${STORM_HOME}/ui.log 2>&1


echo "*********stop nimbus supervisor*********"
kill -9 `ps -ef|grep daemon.supervisor |grep -v grep| awk '{print $2}'|head -1` >${STORM_HOME}/supervisor.log 2>&1 &
echo "*********stop nimbus logviewer*********"
kill -9 `ps -ef|grep daemon.logviewer |grep -v grep| awk '{print $2}'|head -1` >${STORM_HOME}/logviewer.log 2>&1 &

