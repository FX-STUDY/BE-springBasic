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
<h1>스프링 MVC 5-7강 요약</h1>
<br>
<h2>스프링 MVC 전체 구조</h2>
HTTP 요청 <br>
-> 1. 핸들러 조회<br>
-> 핸들러를 처리할 수 있는 핸들러 어댑터 조회<br>
-> 핸들러 어댑터 실행: handle(handler)<br>
-> 핸들러 호출<br>
-> MovdelView 반환<br>
-> viewResolver 호출<br>
-> View 반환<br>
-> 뷰 렌더링: render(model)<br>
-> HTML 응답<br>
<br>
<h2>DispatcherServlet 구조</h2>
스프링 MVC의 프론트 컨트롤러가 디스패처 서블릿임.<br>
-요청의 흐름:<br>
 서블릿이 호출되면<br>
 -> HttpServlet.service() 호출 -> DispatcherServlet의 부모인 FrameworkServlet에서 service()를 오버라이드 해두어 FramworkServlet.service()로 여러 메서드가 호출됨 -> DispatcherServlet.doDispatch() 호출<br>
<br>
<h2>HttpRequestHandler</h2>
서블릿과 가장 유사한 형태의 핸들러.<br>
-동작 방식:<br>
핸들러 매핑으로 핸들러 조회 -> 핸들러 어댑터 조회 -> 핸들러 어댑터 실행<br>
<br>
<h2>InternalResourceViewResolver</h2>
-동작 방식:<br>
핸들러 어댑터 호출 -> ViewResolver 호출 -> InternalResourceViewResolver로 InternalResourceView 반환 -> InternalResourceView로 forward() 호출 -> view.render() 호출<br>
<br>
<h2>@RequestMapping</h2>
요청 정보를 매핑, 해당 URL이 호출되면 그 메서드가 호출됨.<br>
대부분의 속성을 배열로 제공하여 다중 설정이 가능.<br>
-메서드 구분<br>
@RequestMapping(value=“”, method = RequestMethod.GET) -> @GetMapping<br>
@RequestMapping(value=“”, method = RequestMethod.POST) -> @PostMapping<br>
-그 외: Put, Delete, Patch<br>
<br>
<h2>@Controller</h2>
스프링이 자동으로 스프링 빈으로 등록해줌.<br>
<br>
<h2>로그</h2>
-로그 라이브러리: <br>
SLF4J(인터페이스), Logback(구현체)<br>
-로그 선언:<br>
private Logger log = LoggerFactory.getLogger(getClass());<br>
private static final Logger log = LoggerFactory.getLogger(Xxx.class);<br>
@Slf4j(롬복 사용)<br>
-로그 호출:<br>
log.info(“”)<br>
-Level:<br>
TRACE　＞DEBUG > INFO > WARN > ERROR<br>
개발 서버는 debug, 운영 서버는 info로 출력.<br>
-장점:<br>
스레드 정보와 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정 가능.<br>
로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영 서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절 가능.<br>
파일이나 네트워크 등 로그를 별도의 위치에 남길 수 있음<br>
System.out 보다 성능이 좋음<br>
<br>
<h2>@RestController</h2>
반환 값으로 뷰를 찾는 것이 아닌, HTTP 메시지 바디에 바로 입력.<br>
<br>
<h2>@PathVariable</h2>
변수명이 같으면 생략 가능.<br>
<br>
<h2>@RequestHeader(“”)</h2>
특정 HTTP 헤더를 조회.<br>
<br>
<h2>@CookieValue(value=“”, required=)</h2>
특정 쿠키를 조회.<br>
<br>
<h2>요청 파라미터</h2>
-GET - 쿼리 파라미터<br>
메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해 전달.<br>
/request-param?username=hello&age=20<br>
<br>
-POST – HTML Form<br>
메시지 바디에 쿼리 파라미터 형식으로 전달<br>
content-type: application/x-www-form-urlencoded<br>
username=hello&age=20<br>
<br>
-HTTP message body에 데이터를 직접 담아서 요청<br>
데이터 형식은 주로 JSON, HTTP API에서 주로 사용<br>
<br>
<h2>@RequestParam</h2>
파라미터 이름으로 바인딩.<br>
HTTP 파라미터 이름이 변수 이름과 같다면 생략 가능.<br>
파라미터 이름만 있고 값이 없는 경우에는 빈 문자로 통과.<br>
<br>
<h2>@ResponseBody</h2>
View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력.<br>
-동작 원리:<br>
HTTP의 body에 문자 내용을 직접 반환 -> viewResolver 대신 HttpMessageConverter 동작<br>
<br>
<h2>@RequestBody</h2>
HTTP 메시지 바디 정보를 조회.<br>
생략 불가능. (생략하면 @ModelAttribute가 됨)<br>
<br>
<h2>@RequestParam Map<>, MultiValueMap<></h2> 
파라미터를 Map, MultiValueMap으로 조회 가능.<br>
<br>
<h2>@Data</h2>
@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 자동으로 적용<br>
<br>
<h2>@ModelAttribute</h2>
HTTP 요청 파라미터나 multipart/form-data 형태의 파라미터를 받아 객체로 사용.<br>
생략 가능하여 @RequestParam과 헷갈릴 수 있음.<br>
-규칙:<br>
String, int, Integer -> @RequestParam<br>
나머지 -> @ModelAttribute<br>
<br>
<h2>InputStream</h2>
HTTP 요청 메시지 바디의 내용을 직접 조회.<br>
<br>
<h2>OutputStream</h2>
HTTP응답 메시지의 바디에 직접 결과 출력.<br>
<br>
<h2>HTTPEntity</h2>
메시지 바디 정보를 직접 조회, 반환 가능.<br>
헤더 정보 포함 가능.<br>
요청 파라미터 조회x, view 조회x.<br>
<br>
<h2>RequestEntity</h2>
HttpMethod, url 정보 추가, 요청에서 사용.<br>
<br>
<h2>요청 매핑 핸들러 어댑터</h2>
-RequestMappingHandlerAdapter 동작 방식:<br>
컨트롤러의 파라미터, 어노테이션 정보를 기반으로 전달 데이터 생성 -> 해당 컨트롤러 호출 –> 컨트롤러의 반환 값을 변환<br>
<br>
<h2>HTTP 메시지 컨버터</h2>
컨트롤러가 필요로 하는 파라미터의 값에 사용됨.<br>
-요청:<br>
ArgumentResolver 가 HTTP 메시지 컨버터를 사용하여 필요한 객체 생성 -> @RequestBody와 HttpEntity 처리<br>
-응답:<br>
ReturnValueHandler 가 HTTP 메시지 컨버터를 호출하여 응답 결과 생성 -> @ResponseBody와 HttpEntity 처리<br>
<br>
<h2>단위 테스트 – JUnit</h2>
-특징:<br>
assert 메서드로 테스트 케이스의 수행 결과를 판별<br>
@Test 메서드가 호출할 때마다 새로운 인스턴스를 생성하여 독립적인 테스트가 가능<br>
-어노테이션<br>
@Test: @Test가 선언된 메서드는 테스트를 수행<br>
@Before: @Test 메서드가 실행되기 전에 해당 메서드가 반드시 실행<br>
@After: @Test 메서드가 실행된 후 해당 메서드가 실행<br>
-given/ when/ then 패턴<br>
준비/ 실행/ 검증.<br>
given: 테스트에 사용하는 변수, 입력값 등을 정의함<br>
when: 실제로 동작하는 테스트를 실행함<br>
then: 예상한 값과 실제 실행을 통해 나온 값을 검증함<br>
<br>
<h2>Thymeleaf</h2>
th:xxx 가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체.<br>
th:xxx 이 없으면 기존 html의 xxx속성이 그대로 사용됨.<br>
-선언:<br>
<html xmlns:th=“http://www.thymeleaf.org”><br>
-표현식:<br>
th:href=“@{/xxx/xxxxx.xxx.xx}”<br>
th:onclick=“|location.href=’@{/xxx/xxx/xx}‘|”<br>
th:each=“item : ${items}”<br>
th:value=“${item.id}”<br>
<br>

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
