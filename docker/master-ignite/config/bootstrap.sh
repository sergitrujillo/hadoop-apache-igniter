#!/bin/bash
rm /tmp/*.pid
echo "starting ssh"
service ssh start
echo "starting dfs"
$HADOOP_HOME/sbin/start-dfs.sh
echo "starting yarN"
$HADOOP_HOME/sbin/start-yarn.sh
echo "start ignite"
$IGNITE_HOME/bin/ignite.sh
