#!/bin/bash
#author zhaoheng
#version 20190923
#propose data-transfer restart script


cd `dirname $0`
./stop.sh
./start.sh
