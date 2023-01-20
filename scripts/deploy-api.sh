#!/bin/bash

REPOSITORY=/home/ec2-user/app/deploy/build/api-server

CURRENT_PID=$(pgrep -f api-server)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

echo "> Build 파일 복사"

mkdir $REPOSITORY/jar
cp $REPOSITORY/build/libs/*.jar $REPOSITORY/jar/


JAR_NAME=$(ls $REPOSITORY/jar/ |grep 'api-server' | head -n 1)

echo "> JAR Name: $JAR_NAME"

source /home/ec2-user/.bash_profile

nohup /opt/jdk-17/bin/java -jar $REPOSITORY/jar/$JAR_NAME --spring.profiles.active=prod > $REPOSITORY/nohup.out 2>&1 &