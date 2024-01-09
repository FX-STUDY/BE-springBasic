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
이번주 개인 공부는<br>
MVC-1 5장 ~ 7장입니다.<br>
Server 개발자의 기본 소양인 Spring MVC 부분 입니다.<br>
눈으로 공부 할  때는 이해하고 있는 것 같지만, 실제 손으로 직접 따라 구현하는 것과는 천지차이입니다. <br>
정리하는게 귀찮을 수 있지만, 저를 믿고 필수로 진행해 주시기 바랍니다.

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
그저 강의 내용을 따라해서는 실력이 늘지 않을 것입니다.<br>
강의 내용의 코딩을 완전 백지 상태에서 하지 못하더라도, <br>
이론은 완벽하게 숙지할 수 있도록 공부 바랍니다. <br>

[ 🧐 Member : Study AND ] <br>
중요 내용에 대해 Readme 요약,<br>
read me 는 필요 시 다시 꺼내서 볼 수 있는 중요자료가 될 것 입니다.

---
## Study 요약
@ServletComponentScan : 서블릿을 직접 등록해서 사용할 수 있도록 하는 기능. 메인 함수 포함한 클래스 위에 사용 <br>
@WebServlet : 어노테이션을 제공하여 해당 어노테이션 안에 경로를 입력하면 클라이언트에서, 해당 경로를 입력할 때 알아서 톰캣서버가 찾아서 실행해준다. <br>
http 요청을 통해 매핑된 url이 호출 -> service() 메서드 실행 <br>
http 요청 데이터 전달 방법 : GET, POST, HTTP message body 직접 담기 <br>
GET : 쿼리 파라미터, 메시지 바디x, url의 쿼리 파라미터에 데이터를 포함하여 전달 <br>
POST : content-type: application/x-www-form-urlencoded, 메시지 바디에 쿼리 파라미터 형식으로 전달 <br>
HTTP message body에 직접 담아서 요청 : HTTP API에서 주로 사용 <br>
@Getter,@Setter : lombok 에서 지원하는 기능으로 getter,setter 구현하지 않아도 알아서 구현해준다. <br>
@AfterEach : 테스트 코드에서 사용하며 각 테스트가 진행된 후마다 사용되는 메서드에 사용한다. <br>
@Test : JUnit에서 테스트를 진행하기 위해 사용되는 어노테이션, 여러개의 어노테이션이 있을 경우 동작순서를 보장하지 않는다. <br>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> : 해당 줄로 시작되는 문서는 jsp문서이다. <br>
<%@ page import ~~ %> : 자바의 import문과 동일 <br>
<% ~~ %> : 자바 코드 입력 가능 <br>
<%= ~~ %> : 자바 코드 출력 가능 <br>
mvc패턴 개요 : Model, Controller, View <br>
Model : 뷰에 출력할 데이터를 담아둔다, 뷰가 필요한 데이터를 모델에 담아서 전달 <br>
View : 모델에 담겨있는 데이터를 사용하여 화면을 그리는 일을 담당. <br>
Controller : HTTP 요청을 받아서 파라미터를 검증, 비즈니스 로직을 실행, 뷰에 전달할 결과 데이터를 조회하여 모델에 담는다. <br>
FrontController 패턴 특징 : 프론트 컨트롤러 서블릿 하나로 클라이언트 요청을 받음, 프론트 컨트롤러가 요청에 맞는 컨트롤러 호출, 공통 처리 가능, 프론트 컨트롤러 제외 나머지 컨트롤러는 서블릿 사용x <br>
뷰 리졸버 : 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경, 실제 물리 경로가 있는 객체 반환 <br>
핸들러 어댑터 : 중간에 어댑터 역할을 하는 어댑터, 다양한 종류의 컨트롤러 호출하는 기능 <br>
핸들러(컨트롤러) : 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경, 핸들러 어댑터가 존재하여 컨트롤러의 개념 뿐 아닌 어떤것이든 어댑터와 연결하여 처리 가능 <br>


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
