
## MOMO Project  _SERVER
기획자 분들의 요구사항에 맞춰 협업한 프로젝트 입니다
* 김남주, 신동금

![image](https://user-images.githubusercontent.com/73453283/164159577-6fe902f8-7b33-4177-98d0-7bc8ca8a8b6d.png)

### 2022-04-18 개발상황
* API 명세서 제작
* [기능 구현 정리](https://docs.google.com/spreadsheets/d/1N9hlroYr8jF329h9Pk4APd2NBekYUOnbyEw2vAlHXUM/edit?usp=sharing) 

### 2022-04-19 개발상황
* AWS 서버 구축 Nginx 설치
* SSL을 사용하여 HTTPS 적용
* Topic CRUD API 생성

### 2022-04-20 개발상황
* CORS 에러 해결 
* Topic Entity CreateAt 추가 (프론트님 요구사항) 
* Topic CRUD 서버 업데이트

### 2022-04-21 개발상황
* User CRUD API 생성
* Post CRUD API 생성
* Swagger 적용
* POST CRUD 수정 + DB변경 (프론트님 요구사항)

### 2022-04-22 개발상황
* CORS 에러 해결 2
* 로그인 API 테스트

### 2022-04-23 개발상황
* 나의 게시글 확인 기능 API 생성

### 2022-04-24 개발상황
* swagger 명세서 업데이트
* 데이터베이스 초기 데이터 삽입 (기획자님 요구사항)
* JWT 토큰 로그인 회원가입 구현


---
1. 로그인후 작동이 되는지
3. 응답값 보이지 않도록 해야함 
4. 토큰이 (세션) 쿠키로 보내져야함

5. 회원가입후 -> 로그인을 토큰값이 넘어오면 -> 프론트가 저장
로그인이 된상태에서만 작동되도록 헤더에 토큰값을 넣어줬음
   (헤더에 토큰값을 넣은게 누구인지는 모르겠다.)


