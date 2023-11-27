![header](https://capsule-render.vercel.app/api?type=soft&color=auto&height=150&section=header&text=UserManagement&fontSize=90&animation=blink&align=center)
<a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FFX-STUDY%2FBE-STUDY%2Fhit-counter&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/></a>
--
## Tech Stack
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
## DB
![Memory](https://img.shields.io/badge/Memory-000000?style=for-the-badge&logo=memory&logoColor=white)
## ORM
![OMR](https://img.shields.io/badge/NONE-000000?style=for-the-badge&logo=NONE&logoColor=white)
## IDE
![intelliJ](https://img.shields.io/badge/IntelliJIDEA-000000?style=for-the-badge&logo=IntelliJIDEA&logoColor=white)
## TEST
![Junit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white)
## SCM
![GITHUB](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
--
# 목적
[ F(X) 동아리 실력 증진 ] <br><br>
🏃🏻‍♂️끈기, 💦열정, 🧡욕구, 👊🏻인사이트
## 요구사항
프로젝트 생성
스프링 부트 스타터 사이트로 이동해서 스프링 프로젝트 생성 <br>
https://start.spring.io <br>
프로젝트 선택 <br>
Project: Gradle Project Language: Java Spring Boot: 2.7.x <br>
3.0 이상 사용시 자바 17 이상, jakarta pakage 변경 됨을 유의할 것<br>
Project Metadata Group: hello <br>
Artifact: item-service <br>
Name: item-service <br>
Package name: hello.itemservice Packaging: Jar (주의!) <br>
Java: 11 or 17 <br>
Dependencies: Spring Web, Thymeleaf, Lombok <br>

상품을 관리할 수 있는 서비스를 만들어보자.
상품 도메인 모델
- 상품 
- ID 
- 상품명 
- 가격 
- 수량

상품 관리 기능
- 상품 목록 
- 상품 상세 
- 상품 등록 
- 상품 수정

![스크린샷 2023-11-26 09.43.53.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-26%2009.43.53.png)
![스크린샷 2023-11-26 09.43.57.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-26%2009.43.57.png)
![스크린샷 2023-11-26 09.44.01.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-26%2009.44.01.png)
![스크린샷 2023-11-26 09.44.06.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-26%2009.44.06.png)
![스크린샷 2023-11-26 09.44.10.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-26%2009.44.10.png)

## Study 방법
[ 😎 Leader's 요구사항 ] <br>

미션 제출기간은 3주 입니다.<br>
MVC Pattern 을 사용하고 단위별 Test 가 필요합니다. <br>
ex ) item DAO test, item Service Test , Connection Test ... <br>
서비스 제공 흐름을 참고하여 진행 바랍니다. <br>
정답은 방학일에 제공됩니다. <br>ss
commit은 기능 단위로 요청하시기 바랍니다.


[ 🧐 Member : Study AND ] <br>
   - main fork 동기화 후 작업 진행
   - 개인 folder 없습니다. 이전 Answer file 활용해서 작성 바랍니다.
   - ANSWER README 에 작성 하되, 기본 포맷은 기본으로 작성하고, 개별 Custom 후 추가 정보 기입
---

## Commit Message ROLE
Header, Body, Footer는 빈 행으로 구분한다 <br>
... <br>
타입(스코프): 주제(제목) // Header(헤더) <br>

본문 // Body(바디) <br>

바닥글 // Footer <br>
... <br>

|타입이름|내용|
|------|---|
|feat|새로운 기능에 대한 커밋|
|fix|버그 수정에 대한 커밋|
|build|빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋|
|chore|그 외 자잘한 수정에 대한 커밋|
|ci|ci 관련 설정 수정에 대한 커밋|
|docs|문서 수정에 대한 커밋|
|style|코드 스타일 혹은 포맷 등에 관한 커밋|
|refactor|코드 리팩토링에 대한 커밋|
|test|테스트 코드 수정에 대한 커밋|
|perf|성능 개선에 대한 커밋|

매주 README 커밋 -> DOCS <br>
mission 풀이 후 커밋 -> feat <br>
PR 후 수정 사항 커밋 -> perf <br>
