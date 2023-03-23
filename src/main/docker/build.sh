#!/bin/sh

registry=registry.tools.orange-sonatel.com/dif/api

docker build -t $registry/sonatel-api-demo:1.3.0 .
docker push $registry/sonatel-api-demo:1.3.0