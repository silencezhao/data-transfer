#!/bin/bash
#author zhaoheng
#version 20190923
#propose data-transfer stop script


cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

PIDS=`ps -ef | grep -v grep| grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: data-transfer does not started!"
    exit 1
fi

echo -e "Stopping the data-transfer ...\c"
for PID in $PIDS ; do
    kill -15 $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done
echo -e "\ndata-transfer has stopped!"