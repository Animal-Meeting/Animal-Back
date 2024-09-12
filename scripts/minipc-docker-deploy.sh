#!/bin/bash

# 배포 로그 파일 설정
DEPLOY_LOG="/home/hong/app/animal-back/deploy.log"

# 현재 디렉토리 이동
cd /home/hong/app/animal-back

# 새로운 Docker 이미지 빌드
echo "### Docker 이미지 빌드 시작 ..." >> $DEPLOY_LOG
docker-compose build >> $DEPLOY_LOG 2>&1

if [ $? -ne 0 ]; then
  echo "Docker 이미지 빌드 실패!" >> $DEPLOY_LOG
  exit 1
fi

echo "### Docker 이미지 빌드 완료!" >> $DEPLOY_LOG

# 기존에 실행 중인 컨테이너 중지 및 삭제
echo "### 기존 컨테이너 종료 및 삭제 ..." >> $DEPLOY_LOG
docker-compose down >> $DEPLOY_LOG 2>&1

if [ $? -ne 0 ]; then
  echo "기존 컨테이너 종료 및 삭제 실패!" >> $DEPLOY_LOG
  exit 1
fi

# 새로운 컨테이너 실행
echo "### Docker 컨테이너 실행 시작 ..." >> $DEPLOY_LOG
docker-compose up -d >> $DEPLOY_LOG 2>&1

if [ $? -ne 0 ]; then
  echo "Docker 컨테이너 실행 실패!" >> $DEPLOY_LOG
  exit 1
fi

echo "### Docker 컨테이너 실행 완료!" >> $DEPLOY_LOG