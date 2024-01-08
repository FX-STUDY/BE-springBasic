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
MVC-1 1장 ~ 4장입니다.<br>
본격 Spring을 시작하기전 필수 코스 입니다.<br>
잘 몰랐던 개념이 있다면 다시 숙지하는 마음으로 공부하시면 됩니다<br>

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
그저 강의 내용을 따라해서는 실력이 늘지 않을 것입니다.<br>
강의 내용의 코딩을 완전 백지 상태에서 하지 못하더라도, <br>
이론은 완벽하게 숙지할 수 있도록 공부 바랍니다. <br>

[ 🧐 Member : Study AND ] <br>
중요 내용에 대해 Readme 요약,<br>
read me 는 필요 시 다시 꺼내서 볼 수 있는 중요자료가 될 것 입니다.

---
<h1>스프링 MVC 1-4강 요약</h1><br>
<br>
<h2>MVC 패턴</h2>
Model View Controller: JSP로 처리하던 것을 Controller와 View의 영역으로 역할을 나눈 것.<br>
-모델: 뷰에 출력할 데이터를 담아둠<br>
-컨트롤러: HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행, 뷰에 전달할 결과 데이터를 조회해서 모델에 담아둠<br>
-뷰: 모델에 담겨있는 데이터를 사용해서 화면을 출력<br>
<br>
!컨트롤러에 비즈니스 로직을 두면 너무 많은 역할을 담당하게 되어, 서비스라는 계층을 별도로 만들어 처리함. 컨트롤러는 비즈니스 로직이 있는 서비스를 호출하는 역할을 담당.<br>
<br>
<h2>MVC 패턴의 한계</h2><br>
-MVC 컨트롤러의 단점:<br>
 포워드 중복: View로 이동하는 코드가 항상 중복 호출되어야함<br>
 ViewPath의 중복, jsp가 아닌 thymeleaf같은 다른 뷰로 변경하면 전체 코드를 다 변경해야함<br>
 사용하지 않는 코드: HttpServletRequest, HttpServletResponse등을 사용할 때도 있고, 하지 않을 때도 있음<br>
 공통 처리의 어려움: 기능이 복잡해질수록 컨트롤러에서 공통으로 처리해야하는 부분이 증가, 실수로 호출하지 않으면 문제가 됨<br>
<br>
<h2>프론트 컨트롤러</h2><br>
프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받아 맞는 컨트롤러를 찾아서 호출<br>
-특징: 공통 처리 기능, 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨<br>
<br>
<h2>프론트 컨트롤러 도입 V-1</h2><br>
urlPatterns=“/front-controller/v1/*” 로 지정하여 /front-controller/v1을 포함한 하위 모든 요청이 이 서블릿에서 받아들이도록 함<br>
-주요 코드:<br>
urlPatterns 지정<br>
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")<br>
url 매핑<br>
public FrontControllerServletV1() {<br>
 controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());<br>
 controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());<br>
 controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());<br>
 }<br>

<h2>프론트 컨트롤러 View 분리 V-2</h2><br>
모든 컨트롤러에 뷰로 이동하는 부분이 중복됨<br>
-주요 코드:<br>
객체를 생성하여 뷰 이름을 넣고 반환<br>
return new MyView("/WEB-INF/views/new-form.jsp");<br>
return new MyView("/WEB-INF/views/save-result.jsp");<br>
return new MyView("/WEB-INF/views/members.jsp");<br>
<br>
<h2>프론트 컨트롤러 Model 추가 V-3</h2><br>
-서블릿 종속성 제거:<br>
 컨트롤러 입장에서 HttpServletRequest, HttpServletResponse는 꼭 필요하지 않음<br>
 요청 파라미터 정보는 Map으로 대신 넘기도록하면 컨트롤러가 서블릿 기술을 몰라도 동작 가능함<br>
-뷰 이름 중복 제거:<br>
 컨트롤러는 뷰의 논리 이름을 반환하고, 실제 물리 위치의 이름은 프론트 컨트롤러에서 처리하도록 함<br>
-뷰 리졸버:<br>
 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경시킴, 실제 물리 경로가 있는 객체를 반환<br>
-주요 코드:<br>
뷰의 논리적인 이름 지정<br>
@Override<br>
 public ModelView process(Map<String, String> paramMap) {<br>
 return new ModelView("new-form");<br>
 }<br>
뷰의 실제 물리 경로가 있는 객체 반환<br>
MyView view = viewResolver(viewName);<br>
private MyView viewResolver(String viewName) {<br>
 return new MyView("/WEB-INF/views/" + viewName + ".jsp");<br>
 }<br>
<br>

<h2>프론트 컨트롤러 단순하고 실용적인 V-4</h2><br>
ModelView 객체를 생성하고 반환하는 부분이 번거로우므로, 컨트롤러가 ModelView대신 ViewName만 반환하도록 함.<br>
모델이 파라미터로 전달되기 때문에, 모델을 직접 생성하지 않아도 됨.<br>
-주요 코드:<br>
뷰의 논리 이름 반환<br>
@Override<br>
 public String process(Map<String, String> paramMap, Map<String, Object> model) {<br>
 return "new-form";<br>
 }<br>
<br>
<h2>프론트 컨트롤러 유연한 컨트롤러 V-5</h2><br>
컨트롤러V3과 컨트롤러V4는 완전히 다른 인터페이스이기 때문에 호환이 불가능.<br>
어댑터 패턴을 사용하여 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 변경.<br>
-핸들러 어댑터:<br>
 어댑터 역할을 해주어 다양한 종류의 컨트롤러를 호출<br>
-핸들러:<br>
 컨트롤러를 더 넓은 범위의 핸들러로 변경<br>
-주요 코드:<br>
 어댑터 인터페이스<br>
public interface MyHandlerAdapter {<br>
 boolean supports(Object handler);<br>
 ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;<br>
}<br>
어댑터 구현<br>
@Override<br>
 public boolean supports(Object handler) {<br>
 return (handler instanceof ControllerV4);<br>
 }<br>




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
