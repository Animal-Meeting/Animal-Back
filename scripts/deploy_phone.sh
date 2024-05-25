#!/bin/bash

# S3에서 파일을 다운로드할 버킷 이름과 파일 이름
BUCKET_NAME="animal-meeting-bucket"
FILE_NAME="animal-meeting-app-phone.zip"

# S3에서 파일을 다운로드할 경로
DOWNLOAD_PATH="~$FILE_NAME"

# S3로부터 파일 다운로드
aws s3 cp s3://$BUCKET_NAME/$FILE_NAME $DOWNLOAD_PATH --region ap-northeast-2

# 다운로드가 성공했는지 확인
if [ $? -eq 0 ]; then
    echo "File downloaded successfully from S3."
else
    echo "Failed to download file from S3."
    exit 1
fi

# 스프링 JAR 파일 실행
#java -jar $DOWNLOAD_PATH

# 스크립트 종료
exit 0
