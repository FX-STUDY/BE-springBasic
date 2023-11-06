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
[ 회원 ] <br>
회원을 가입하고 조회할 수 있다. <br>
회원등급 일반 / VIP 등급이 있다. <br>
회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정) <br>
회원 ID (자동 생성), 회원 이름, 등급
[ 주문과 할인 정책 ] <br>
회원은 상품을 주문할 수 있다. <br>
회원 등급에 따라 할인 정책을 적용할 수 있다. <br>
할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인 적용 (나중에 변경 될 수 있다.) <br>
할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정) <br>
주문 번호 (자동 생성), 상품 이름, 상품 가격, 할인 받은 가격

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
순수한 자바 기술을 사용하여 요구사항을 만족하는 Program 을 작성하세요. <br>
작성 과정에서 불편한 점이 무엇이 있었는지 정리하세요. <br>

[ 🧐 Member : Study AND ] <br>
   - reader 출제 branch 를 기준으로 분기 branch 생성 ( 본인 영어 이름 : 별칭 가능 ) <br>
   - code 작성 및 문제 해결 과정에서 느낀점, 배운점 작성 <br>
   
### ReadMe 제출 양식
1. Mission Title
2. Mission 주요 이론 요약
3. ISSUE
4. Solution
5. About
<hr>

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
