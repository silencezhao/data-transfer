#!/bin/bash
#author zhaoheng
#version 20190923
#propose data-transfer server script


cd `dirname $0`

case "$1" in
    "stop")
        ./stop.sh
        ;;
    "start")
        ./start.sh
        ;;
    "restart")
        ./restart.sh
        ;;
    "start_daemon")
        ./start_daemon.sh
        ;;
    *)
        echo -e "usage: server.sh [stop|start|restart|start_daemon]\n\tstart\tstart data-transfer\n\tstop\tstop data-transfer\n\trestart\trestart data-transfer\n\t"
        exit;
esac