#!/usr/bin/env bash

echo -e "Building Account API application ...\n"
mvn package -DskipTests

echo -e "\nStop running account-api container and remove it ...\n"

docker stop $(docker ps -a | grep account-api | awk '{print $1}')
docker rm $(docker ps -a | grep account-api | awk '{print $1}')

echo -e "\nStart Docker Compose...\n"

cd docker

docker-compose -f docker-compose.yml up --build --force-recreate --abort-on-container-exit