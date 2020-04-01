#!/bin/bash
#author zhaoheng
#version 20190923
#propose data-transfer start script



JAVA_HOME=/opt/nsfocus/espc/deps/java
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
cd ..
COMMON_DIR=`pwd`
cd $DEPLOY_DIR
CONF_DIR=$DEPLOY_DIR/conf
LIB_DIR=$DEPLOY_DIR/lib/*
SERVER_NAME=DATA-TRANSFER

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps -ef | grep java | grep -v grep | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi
nohup $JAVA_HOME/bin/java -Xms1024m -Xmx3096m  -classpath $CONF_DIR:$LIB_DIR com.nsfocus.core.Boot 1>/dev/null 2>./start.err &

echo -e "Starting the $SERVER_NAME ...\c"

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    start_pid=`ps -ef | grep java | grep -v grep | grep "$CONF_DIR" |awk '{print $2}'`
    if [ -n "$start_pid" ]; then
        COUNT=1
    fi
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

PIDS=`ps -ef | grep java |  grep -v grep | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo -e "\nSTART DATA-TRANSFER SUCCESS!PID IS $PIDS"