#!/bin/bash

APP_DIR=~/offerup
STDOUT_LOG=$APP_DIR/logs/stdout.log
if [ -e "$STDOUT_LOG" ]; then
    mv $STDOUT_LOG{,-$(date +'%Y%m%d.%H%M%S')}
fi

APP_JAR=itemprices-0.0.1-SNAPSHOT.jar
LOG4J_CONFIG=classpath:config/log4j.prod.xml
JVM_ARGS="-Derror.whitelabel.enabled=false -server -d64 -Djava.awt.headless=true"

cd $APP_DIR
exec java $JVM_ARGS -jar $APP_JAR > "$STDOUT_LOG" 2>&1 &

