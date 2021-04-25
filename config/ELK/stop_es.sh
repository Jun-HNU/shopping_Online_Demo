#!/bin/bash

export ES_HOME=/home/elk/elasticsearch-6.3.1

su elkuser -c "kill -s 9 `cat ${ES_HOME}/pid`"