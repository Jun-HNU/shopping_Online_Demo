#!/bin/bash

export ES_HOME=/home/elk/elasticsearch-6.3.1

su elkuser -c "sh ${ES_HOME}/bin/elasticsearch -d -p ${ES_HOME}/pid"