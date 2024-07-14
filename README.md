<h1 align="center">
  <a href="https://github.com/Animal-Meeting/Animal-Back" title="AwesomeCV Documentation">
    <!-- <img alt="AwesomeCV" src="https://github.com/stock-price-calculator/tradingbot/assets/77156858/e1be76c5-3bf5-478a-8bc4-c790ef10f3a2" width="100%" height="100%" /> -->

<img src="https://github.com/Animal-Meeting/Animal-Back/assets/77156858/db2882d6-ae15-461d-95af-ac1a8b75e1b4" width="450"/>

  </a>
  <br />
    미팅해요 동물의 숲
</h1>
<p align="center">
   동물상 판별 AI를 통해 사용자의 동물상을 측정하고, 원하는 동물상과 매칭을 시켜주는 서비스입니다. 

</p>

<div align="center">
  <img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/>

  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat&logo=javascript&logoColor=white"/>
    
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=white"/>

<img src="https://img.shields.io/badge/JPA-6DB33F?style=flat&logo=JPA&logoColor=white"/>

 <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=amazonec2&logoColor=white"/>

<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat&logo=amazons3&logoColor=white"/>

<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat&logo=amazonrds&logoColor=white"/>

<img src="https://img.shields.io/badge/Amazon CodeDeploy-527FFF?style=flat&logo=&logoColor=white"/>

<img src="https://img.shields.io/badge/NGINX-009639?style=flat&logo=nginx&logoColor=white"/>

<img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white"/>

<img src="https://img.shields.io/badge/Phone Ubuntu-E95420?style=flat&logo=ubuntu&logoColor=white"/>

<img src="https://img.shields.io/badge/Github-181717?style=flat&logo=github&logoColor=white"/>

<img src="https://img.shields.io/badge/Github Actions-2088FF?style=flat&logo=githubactions&logoColor=white"/>

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white"/>

<img src="https://img.shields.io/badge/Teachable Machine-00539F?style=flat&logo=probot&logoColor=white"/>





</div>

## 📌 개요
- 프로젝트 이름 : 미팅해요 동물의 숲
- 개발 배경 : 2024 세종대학교 축제 낮부스 운영
- 개발 언어 : Java, Javascript
- 프론트 : React, Recoil
- 백엔드 : Spring Boot, Spring JPA, MySql, EC2, S3, RDS, CodeDeploy, Docker, Nginx
- AI 모델 : Teachable Machine


## 🖥️ 프로젝트 소개
- 2024 세종대학교 축제(05.29 - 05.31) 낮 부스를 운영하였습니다.
- **AI 동물상 측정**을 통해 사용자의 동물상을 측정할 수 있습니다.
- 측정 결과 이미지를 다운로드 할 수 있습니다.
- 선호하는 동물상을 선택하고 **미팅에 참여**할 수 있습니다.


 
 ### 🔗 프로젝트 링크
- <a href="https://github.com/TellMeThe-Answer/Server">AI 동물상 측정</a>
- <a href="https://github.com/SejongPeer/SejongPeerFront">미팅 등록 및 미팅 참여</a>
- <a href="https://github.com/Animal-Meeting/Animal-Back">미팅해요 동물의 숲 백엔드</a>
- <a href="https://github.com/Animal-Meeting/Animal-Image-Crawling">구글 크롬 이미지 크롤링</a>
- <a href="https://github.com/Animal-Meeting/Animal-Image-Preprocessing">이미지 전처리</a>

<br/>





## ⚙️ 프로젝트 주요 기능

### 1. 🐶 [AI 동물상 측정](#1--ai-동물상-측정-1)
- 남자 (강아지, 고양이, 토끼, 공룡, 곰, 늑대) / 여자(강아지, 고양이, 토끼, 사막여우, 사슴, 햄스터) 각각 6가지 동물상
- 5초 동안 나온 동물상의 확률을 누적합으로 측정결과 리턴
- 남/여 서로 다른 동물상 6개에서 도합 100%로 결과 도출
- 측정 완료 후 각 동물상의 특징과 대표 연예인 사진

### 2. 📷 [측정 결과 이미지 등록](#2--측정-결과-이미지-등록-1)
- AI 동물상 측정 후 고유번호(학번 or 전화번호)로 등록
- 결과 사진에 대표 연예인과 동물캐릭터, 동물상 특징

### 3. 📷 [측정 결과 이미지 다운로드](#3--측정-결과-이미지-다운로드-1)
- 웹사이트에서 고유번호 입력 후 이미지 다운로드
- 현장 측정 사진 or 휴대폰 사진 다운로드 가능

### 4. 👬 [미팅 등록](#4--미팅-등록-1)
- 1대1, 2대2, 3대3 으로 미팅참여 가능
- 선호하는 동물상 1순위, 2순위 선택 후 미팅 등록

### 5. 👬 [미팅 결과 확인 및 참여](#5--미팅-결과-확인-및-참여-1)
- 매칭 완료 후 이름, 전화번호로 로그인
- 상대방 동물상 확인 후 오픈채팅 참여


<br/>


##  💾 시스템 설계도
<img src="https://github.com/33ohoh/pestback/assets/77156858/16b1b2ca-84d1-43f3-af6d-ea0a1ae31d0d" height="100%" width="100%" >

<hr>




## 📌 페이지 화면
### 1. 🐶 AI 동물상 측정

**🖥️ 모니터 버전**
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/42d09b5d-1c80-47e9-b729-596a06dca52c"  width="80%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/92c0813c-a466-4ecb-b547-10ba428a7318"  width="93%" > |
|:----------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           동물상 측정                                                           |                                                            동물상 측정2                                                            |  

</div>

<br/>

**📺 TV 버전**

<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/ae9a1521-5d1e-4a07-871a-61d1dd6ae112"  width="100%"> |
:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                            메인페이지                                                           |                                                       

</div>
<div align="center">

| <img src="https://github.com/Animal-Meeting/Animal-Back/assets/77156858/89e63e8a-05af-4362-b4a9-b0839b0d6887"  width="100%"> |
:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                            화면 1                                                           |                                                       

</div>
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/9680ff7f-f297-4aaf-bf5e-397693f9dade"  width="100%"> |
:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                            화면 2                                                           |                                                       

</div>
<hr>
<br/>

### 2. 📷 측정 결과 이미지 등록
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/9bc5e5b2-ac42-417c-a086-ff46a79785c7"  width="100%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/e21d79ef-f27e-4767-ae3b-9cf45aff3a75"  width="100%" > | <img src="https://github.com/33ohoh/pestback/assets/77156858/5d300848-da42-4782-9743-1c9ecc4fa1f3"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                             등록하기1                                                              |                                                         등록하기2                                                           |                                                           등록하기3                                                            |

</div>
<br/>

### 3. 📷 측정 결과 이미지 다운로드
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/631ee8fe-2fc0-4c50-a106-ef091a26aee7"  width="100%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/c15272c5-e54f-49a2-8aeb-acf207b6cd2c"  width="100%" > | <img src="https://github.com/33ohoh/pestback/assets/77156858/ca788a72-7cfa-4270-aeaa-14c3bc569e13"  width="83%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                             다운로드1                                                             |                                                         다운로드2                                                           |                                                           다운로드3                                                            |

</div>

<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/c8155cf3-7a5b-47b1-8fa8-e2e23f68f177"  width="82%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/5c99dbc8-2e4e-4caa-9592-cd23d4dc52c5"  width="100%" > | 
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|
|                                                             현장 측정 이미지 버전                                                           |                                                         휴대폰 버전                                                           |                                                           

</div>

<hr>
<br/>

### 4. 👬 미팅 등록
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/e6c67a85-23a5-4358-a7db-9c041f6b56ef"  width="100%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/6b2aa3a3-810a-458c-a430-9e48f54c5a33"  width="100%" > | <img src="https://github.com/33ohoh/pestback/assets/77156858/46f4ae73-5440-4da3-a1b3-729919b43a26"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           미팅 등록 1                                                            |                                                            미팅 등록 2                                                     |                                                             미팅 등록 3                                                 |

</div>
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/f9b3dc02-2a9d-4621-9cee-333245b2ea0b"  width="100%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/9e279e48-e3b9-4859-af5e-c304a1b429fe"  width="100%" > | <img src="https://github.com/33ohoh/pestback/assets/77156858/c5a80000-241d-4e6a-a4ae-c4f2a6cc8bc3"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           미팅 등록 4                                                            |                                                            미팅 등록 5                                                             |                                                            미팅 등록 6                                                        |

</div>

<hr>
<br/>

### 5. 👬 미팅 결과 확인 및 참여
<div align="center">

| <img src="https://github.com/33ohoh/pestback/assets/77156858/34745c0b-8bd7-4f73-ad11-1ed0238fab88"  width="100%"> | <img src="https://github.com/33ohoh/pestback/assets/77156858/469c9c9a-2b86-4277-b0ed-ff498cd006ba"  width="100%" > | 
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|
|                                                             로그인                                                             |                                                            매칭 결과 확인                                                       |                                                           

</div>
<hr>



