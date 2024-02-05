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
회원 가입과 DB 에 등록되는 정보, 수정에 필요한 정보가 서로 다릅니다. <br>
Example ] <br>
### 회원가입 Form <br>
```text
학번
이름
생년월일
학과
학기
주소
학년
```
### DB 저장
```text
학번
학년
이름
생년
생월
생일
학과
학기
주소
```
### 정보 수정 Form
```text
이름
주소
```
### Validation
```text
1. 학번 
- 7자리, 숫자 ( 영문 , 특수 기호 금지 )
2. 이름
- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)
3. 생년월일
- format : YYYY_mm_dd
4. 학과 
- 소프트웨어, 컴퓨터공학, 산업디자인학과 외 금지
5. 학기
- 1학기 , 2학기 외 금지
6. 주소
 - null 허용
7. 학년
- 1 학년 ~ 5학년 외 금지
```
위 검증 사항을 모두 지키면서 각각의 입력 형식에 맞춰 프로그래밍 해주세요.

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
주어진 교재는 최대한 참조하지 마시고,<br>
애매한 요구사항에 대해서는 물어보시기 바랍니다.<br>

[ 🧐 Member : Study AND ] <br>
로직에 대해 왜 이런 방법을 선택했는지? <br>
로직을 짜면서 어떤 느낀점 , 어려운점이 있었는 지 정리하시기 바랍니다.

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
