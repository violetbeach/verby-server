#!/bin/bash

deploy_module "verby-consumer-server" "internal-consumer-server"

function deploy_module {
  local MODULE=$1
  local JAR_NAME=$2
  local REPOSITORY_PATH="/home/ec2-user/deploy/$MODULE"

  local current_pid=$(pgrep -f "$JAR_NAME")

  echo "$current_pid"

  if [ -z "$current_pid" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
  else
    echo "> kill -2 $current_pid"
    kill -9 "$current_pid"
    sleep 5
  fi

  echo "> 새 어플리케이션 배포"

  echo "> Build 파일 복사"

  jar_name=$(ls "$REPOSITORY_PATH/" | grep "$JAR_NAME" | head -n 1)

  echo "> JAR Name: $jar_name"

  source /home/ec2-user/.bash_profile

  nohup /opt/jdk-17/bin/java -jar "$REPOSITORY_PATH/$jar_name" --spring.profiles.active=prod > "$REPOSITORY_PATH/nohup.out" 2>&1 &
}