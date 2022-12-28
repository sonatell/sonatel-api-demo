#!/bin/sh

echo "The application is starting... "
exec java -XshowSettings:vm ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "/home/demo/sonatel-api-demo.jar" "$@"
