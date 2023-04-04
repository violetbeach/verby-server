#!/bin/bash

# $MODULES 환경 변수를 읽어와서 $MODULES_LIST 배열로 변환
IFS=',' read -r -a MODULES_LIST <<< "$MODULES"

# $MODULES_LIST 배열을 순회하며 각 모듈을 처리
for MODULE in "${MODULES_LIST[@]}"
do
  # 변경된 모듈에 대한 작업 수행
  deploy_module($MODULE)
done

function deploy_module {
  local repository_path="/home/ec2-user/app/deploy/build/${MODULE}"
  local current_pid=$(pgrep -f "${MODULE}")

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

  mkdir "$repository_path/jar"
  cp "$repository_path/build/libs/"*.jar "$repository_path/jar/"

  jar_name=$(ls "$repository_path/jar/" | grep "${MODULE}" | head -n 1)

  echo "> JAR Name: $jar_name"

  source /home/ec2-user/.bash_profile

  nohup /opt/jdk-17/bin/java -jar "$repository_path/jar/$jar_name" --spring.profiles.active=prod > "$repository_path/nohup.out" 2>&1 &
}