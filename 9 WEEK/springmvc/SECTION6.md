# 6. 스프링 MVC - 기본 기능

---

## 로깅 간단히 알아보기

운영 시스템에서는 `System.out.println()`같은 시스템 콘솔을 사용해서 필요한 정보를 출력하지 않고, 
별도의 로깅 라이브러리를 사용해 로그를 출력한다.<br>


**로깅 라이브러리**<br>
스프링 부트 라이브러리를 사용하면 스프링 부트는 로깅 라이브러리(`spring-boot-starter-logging`)가 함께 포함된다.
<br>스프링 부트 로깅 라이브러리는 기본으로 아래와 같은 라이브러리를 사용한다.
- SLF4J 
- Logback


로그 라이브러리는 Logback, Log4J, Log4J2 등등 수 많은 라이브러리가 있는데,
그것을 통합해서 인터페이스로 제공하는 것이 바로 SLF4J 라이브러리다.
<br>SLF4J는 인터페이스이고, 그 구현체로 Logback 같은 로그 라이브러리를 선택하면 된다.
실무에서는 스프링 부트가 기본으로 제공하는 Logback을 대부분 사용한다.


**로그 선언**
- `private Logger log = LoggerFactory.getLogger(getClass());`
- `private static final Logger log = LoggerFactory.getLogger(Xxx.class)`
- `@Slf4j` : 롬복 사용 가능


**로그 호출**
- `log.info("hello")`
- `System.out.println("hello")`


*LogTestController*
```java
package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);


        return "ok";
    }
}
```

**매핑 정보**
- `@RestController`
  - `@Controller`는 반환 값이 `String`이면 뷰 이름으로 인식. 그래서 **뷰를 찾고 뷰가 렌더링**된다.
  - `@RestController`는 반환 값으로 뷰를 찾는 것이 아니라, **HTTP 메시지 바디에 바로 입력**한다.
    따라서 실행 결과로 ok 메시지를 받을 수 있다. `@ResponseBody`와 관련이 있다.


**테스트**
- 로그가 출력되는 포멧 확인
  - 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스 명, 로그 메시지
  - 로그 레벨 설정을 변경해 출력 결과 확인
    - LEVEL : `TRACE > DEBUG > INFO > WARN > ERROR`
    - 개발 서버는 debug 출력
    - 운영 서버는 info 출력
  - `@Slf4j`로 변경

**로그 레벨 설정**

*application.properties*
```
logging.level.root=info
logging.level.hello.springmvc=debug
```


**올바른 로그 사용법**
- log.debug("data="+data)
    - 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다. 결과적으
    로 문자 더하기 연산이 발생한다. < 사용 X
  - log.debug("data={}", data)
    - 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이 발생하지
    않는다.



**로그 사용시 장점**
- 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있음.
- 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 
운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절할 수 있음.
- 시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있음.
특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능.
- 성능도 일반 System.out보다 좋음👍. (내부 버퍼링, 멀티 쓰레드 등등) 그래서 실무에서는 꼭 로그를 사용해야 함


---


## 요청 매핑


*MappingController*
```java
package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }
}
```
- `@RestController`
  - `@Controller`는 반환 값이 `String`이면 뷰 이름으로 인식. 따라서 **뷰를 찾고 뷰가 렌더링**됨.
  - `@RestController`는 반환 값으로 뷰를 찾는 것이 아니라, **HTTP 메시지 바디에 바로 입력**됨.
- `@RequestMapping("/hello-basic")`
  - `/hello-basic` URL 호출이 오면 이 메서드가 실행되도록 메핑.
  - 대부분의 속성을 `배열[]`로 제공하므로 다중 설정이 가능 ex) `{"/hello-basic, "/hello-go"}`



**HTTP 메서드**<br>
`@RequestMapping`에 `method`속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출됨.<br>
모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE


**HTTP 메서드 매핑**
```java

//    @RequestMapping(value = "/mapping-get-v2", method = RequestMethod.GET)
    @GetMapping("/mapping-get-v2") //축약
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }
```
만약 여기에 GET 외의 요청, 즉 POST 요청을 하면 스프링 MVC는 HTTP 405 상태코드(Method Not Allowed)를 반환.<br>
HTTP 메서드를 축약한 애노테이션을 사용하는 것이 더 직관적임.


**PathVariable(경로변수) 사용**
```java
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }
```
최근 HTTP API는 다음과 같은 리소스 경로에 식별자를 넣는 스타일을 선호.
- `/mapping/userA`
- `/users/1`
- `@RequestMapping`은 URL 경로를 템플릿화 할 수 있는데, `@PathVariable`을 사용하면 매칭 되는 부분을 편리하게 조회 가능.
- `@PathVariable`의 이름과 파라미터 이름이 같으면 생략 가능.


**PathVariable 다중 사용**
```java
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }
```


*특정 파라미터 조건 매핑*
```java
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }
```
실행
- http://localhost:8080/mapping-param?mode=debug <br>
잘 사용하지는 않는다


*특정 헤더 조건 매핑*
```java
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
```
HTTP 헤더를 사용해야 한다.


*미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume*
```java
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }
```
HTTP 요청의 Content-Type 헤더를 기반으로 미디어 타입으로 매핑.<br>
만약 맞지 않으면 HTTP 415 상태코드(Unsupported Media Type)을 반환.

예시) consumes 
```
consumes = "text/plain"
consumes = {"text/plain", "application/*"}
consumes = MediaType.TEXT_PLAIN_VALUE
```




*미디어 타입 조건 매핑 - HTTP 요청 Accept, produce*
```java
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
```
HTTP 요청의 Accept 헤더를 기반으로 미디어 타입으로 매핑.<br>
만약 맞지 않으면 HTTP 406 상태코드(Not Acceptable)을 반환.

예시)
```
produces = "text/plain"
produces = {"text/plain", "application/*"}
produces = MediaType.TEXT_PLAIN_VALUE
produces = "text/plain;charset=UTF-8"
```




---

## 요청 매핑 - API 예시

**회원 관리 API**
- 회원 목록 조회 : GET `/users`
- 회원 등록 : POST `/users`
- 회원 조회 : GET `/users/{userId}`
- 회원 수정 : PATCH `/users/{userId}`
- 회원 삭제 : DELETE `/users/{userId}`


*MappingClassController*
```java
package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/mapping/users")
@RestController
public class MappingClassController {


    @GetMapping
    public String user() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId=" + userId;
    }

}
```



---

## HTTP 요청 - 기본, 헤더 조회
애노테이션 기반의 스프링 컨트롤러는 다양한 파라미터를 지원한다.

*RequestHeaderController*
```java
package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "myCookie", required = false) String cookie
    ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";
    }

}
```

- `HttpServletRequest`
- `HttpServletResponse`
- `HttpMethod` : HTTP 메서드를 조회. 
- `Locale` : Locale 정보를 조회.
- `@RequestHeader MultiValueMap<String, String> headerMap` : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회.
- `@RequestHeader("host") String host` : 특정 HTTP 헤더를 조회.
  - 속성 
    - 필수 값 여부 : `required`
    - 기본 값 속성 : `defaultValue`
- `@CookieValue(value = "myCookie", required = false) String cookie` : 특정 쿠키를 조회
  - 속성 
    - 필수 값 여부 : `required`
    - 기본 값 속성 : `defaultValue` 
- `MultiValueMap` : MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있음.
  - HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용.
    - **keyA=value1&keyA=value2**


---

## HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form

### HTTP 요청 데이터 조회
**클라이언트에서 서버로 요청 데이터를 전달할 때 3가지 방법을 주로 사용.**
- GET - 쿼리 파라미터
- POST - HTLM Form
- HTTP message body

### 요청 파라미터 - 쿼리 파라미터, HTML Form
`HttpServletRequest`의 `request.getParameter()`를 사용하면 다음 두가지 요청 파라미터 조회 가능.
- GET - 쿼리 파라미터 전송 
  - `http://localhost:8080/request-param?username=hello&age=20`
- POST, HTML Form 전송
```
POST /request-param ...
content-type: application/x-www-form-urlencoded
username=hello&age=20
```


*RequestParamController*
```java
package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("OK");

    }

}
```


**requset.getParameter()**
- 단순히 HttpServletRequest가 제공하는 방식으로 쿼리 파라미터를 조회함

**Post Form 페이지 생성**<br>
테스트용 HTML Form<br>
*hello-form.html*
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/request-param-v1" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```


---


## HTTP 요청 파라미터 - @RequsetParam
스프링이 제공하는 `@RequestParma`을 사용하면 요청 파라미터를 매우 편리하게 사용 가능.

*requestParamV2 method*
```java
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }
```
- `@RequestParam` : 파라미터 이름으로 바인딩
- `@ResponseBody` : View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력

**@RequestParam**의 `name(value)` 속성이 파라미터 이름으로 사용
- @RequestParam("username") String memberName -> request.getParameter("username")


*requestParamV3 method*
```java
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
```
HTTP 파라미터 이름이 변수 이름과 같으면 `@Request(name ="")` 생략 가능


*requestParamV4 method*
```java
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
```
`String` , `int` , `Integer` 등의 단순 타입이면 `@RequestParam`도 생략 가능


*requestParamRequired method - 파라미터 필수 여부*
```java
/**
 * @RequestParam.required
 * /request-param-required -> username이 없으므로 예외
 *
 * 주의!
 * /request-param-required?username= -> 빈문자로 통과
 *
 * 주의!
 * /request-param-required
 * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
defaultValue 사용)
 */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamV5(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age //int는 null이 못들어감 다만 Integer은 객체라 가능
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
```
- `@RequestParam.required`
  - 파라미터 필수 여부
  - 기본값이 파라미터 필수(`true`)
- `/request-param-required` 요청
  - `username`이 없으므로 400 예회 발생

**주의! - 파라미터 이름만 사용**<br>
`/request-param-required?username=` <br>
파라미터 이름만 있고 값이 없는 경우 빈문자로 통과


**주의! - 기본형(primitive)에 null 입력**
- /request-param 요청
- @RequestParam(required = false) int age

null 을 int 에 입력하는 것은 불가능(500 예외 발생)<br>
따라서 null 을 받을 수 있는 Integer 로 변경하거나, 또는 다음에 나오는 defaultValue 사용


*requestParamDefault method* 
```java
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false , defaultValue = "-1") Integer age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
```
- 파라미터에 값이 없는 경우 `defaultValue` 를 사용하면 기본 값을 적용할 수 있음
- 이미 기본 값이 있기 때문에 `required` 는 의미가 없음

defaultValue 는 빈 문자의 경우에도 설정한 기본 값이 적용됨.<br>
`/request-param-default?username=`


*requestParam method - 파라미터를 Map으로 조회*
```java
/**
 * @RequestParam Map, MultiValueMap
 * Map(key=value)
 * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
 */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),  paramMap.get("age"));
        return "ok";
    }
```
파라미터를 Map, MultiValueMap으로 조회 가능
- `@RequestParam Map`
  - `Map(key=value)`
- `@RequestParam MultiValueMap`
  - `MultiValueMap(key=[value1,value2,....] ex) (key=userIds, value=[id1, id2])` 


파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용



---


# HTTP 요청 파라미터 - @ModelAttribute
스프링은 `@ModelAttribute` 기능을 제공함 

요청 파라미터를 바인딩 받을 객체 생성

*HelloData*
```java
package hello.springmvc.basic;


import lombok.Data;

@Data
public class HelloData {
    private String username;
    private int age;
}
```
- `@Data`
  - `@Getter`, `@Setter`, `@EqualsAndHashCode`, `@RequiredArgsConstructor`를 자동으로 적용해줌


*modelAttributeV1 method*
```java
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }
```
자동으로 `HelloData` 객체가 생성되고, 요청 파라미터 값도 모두 들어간다.<br>

스프링 MVC는 `@ModelAttribute`가 있으면 다음을 실행한다.
- `HelloData` 객체 생성
- 요청 파라미터의 이름으로 `HelloData`객체의 프로퍼티를 조사, 그리고 해당 프로퍼티의 setter를 호출해 파라미터의 값 입력(바인딩).
- ex) 파라미터 이름이 `username`이면 `setUsername()`메서드를 찾아 호출해 값을 입력


**프로퍼티**<br>
객체에 `getUsername()`, `setUsername()`메서드가 있으면, 이 객체는 `username`이라는 프로퍼티를 가짐.<br>
`username`프로퍼티의 값을 변경하면 `setUsername()`이 호출, 조회하면 `getUsername()`이 호출됨.
```java
class HelloData {
    getUsername();setUsername();
}
```


*modelAttributeV2 - @ModelAttribute 생략*
```java
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }
```

`@ModelAttribute`는 생략 가능. 하지만 `@RequestParam`도 생략할 수 있어 혼란이 발생할 수 있음.


스프링은 해당 생략시 다음과 같은 규칙을 적용.
- `String`, `int`, `Integer` 같은 단순 타입 = @RequestParam
- 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)


---


# HTTP 요청 메시지 - 단순 텍스트

- **HTTP message body에 데이터를 직접 담아서 요청** 
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - 데이터 형식은 주로 JSON 사용
  - POST, PUT, PATCH

요청 파라미터와 다르게, HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우는 
`@RequestParam`, `@ModelAttribute`를 사용할 수 없음.(HTTP Form형식으로 전달되는 경우 요청 파라미터로 인정됨)


HTTP 메시지 바디에 데이터를 `InputStream`을 사용해 직접 읽을 수 있음.

*RequestBodyStringController*
```java
package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }
}
```


*requestBodyStringV2 method - Input, Output Stream, Reader*
```java
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }
```

**스프링 MVC는 다음 파라미터를 지원**
- InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
- OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력


*requestBodyStringV3 method - HttpEntity*
```java
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }
```

**스프링 MVC는 다음 파라미터를 지원**
- **HttpEntity : HTTP header, body 정보를 편리하게 조회**
  - 메시지 바디 정보를 직접 조회
  - 요청 파라미터를 조회하는 기능과 관계 없음! `@RequestParam`X, `@ModelAttribute`X
- **HttpEntity**는 응답에도 사용 가능
  - 메시지 바디 정보를 직접 반환
  - 헤더 정보 포함 가능
  - view 조회X
 
`HttpEntity`를 상속받은 다음 객체들도 같은 기능을 제공.
- **RequestEntity**
  - HttpMethod, url 정보가 추가, 요청에도 사용.
- **ResponseEntity**
  - HTTP 상태 코드 설정 가능, 응답에도 사용
  - `return new ResponseEntity<String>("ok", responseHeaders,HttpStatus.CREATED)`


*requestBodyString method - @RequestBody*
```java
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
```

`@RequestBody`<br>
`@RequestBody`를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회 가능. 
헤더 정보가 필요하면 `HttpEntity` or `@RequestHeader`를 사용하면 됨.<br>
이렇게 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 `@RequestParam`, `@ModelAttribute`와 관계 없음!


**요청 파라미터 vs HTTP 메시지 바디**
- 요청 파라미터를 조회하는 기능: @RequestParam , @ModelAttribute
- HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody


**@ResponseBody**<br>
@ResponseBody 를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있음.
물론 이 경우에도 view를 사용하지 않음.


---

## HTTP 요청 메시지 - JSON 


*RequestBodyJsonController*
```java
package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }
}
```

- HttpServletRequest를 사용해서 직접 HTTP 메시지 바디에서 데이터를 읽고, 문자로 변환.
- 문자로 된 JSON 데이터를 Jackson 라이브러리인 objectMapper 를 사용해서 자바 객체로 변환.


*requestBodyJsonV2 - @RequestBody 문자 변환*
```java
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
```

- @RequestBody 를 사용해서 HTTP 메시지에서 데이터를 꺼내고 messageBody에 저장.
- 문자로 된 JSON 데이터인 messageBody 를 objectMapper 를 통해서 자바 객체로 변환.


*requestBodyJsonV3 method - @RequestBody 객체 변환*
```java
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
```

**@RequestBody 객체 파라미터**
- `@RequestBody HelloData data`
- `@RequestBody` 에 직접 만든 객체를 지정할 수 있음.

`HttpEntity` , `@RequestBody` 를 사용하면 HTTP 메시지 컨버터가
HTTP 메시지 바디의 내용을 원하는 문자나 객체 등으로 변환해줌<br>
HTTP 메시지 컨버터는 문자 뿐만 아니라 JSON도 객체로 변환해주는데, 
방금 V2에서 했던 작업을 대신 처리 해줌.


**@RequestBody 생략 불가**<br>
스프링은 `@ModelAttribute` , `@RequestParam` 과 같은 해당 애노테이션을 생략시 다음과 같은 규칙을 적용.
- `String`, `int`, `Integer` 같은 단순 타입 = `@RequestParam`
- 나머지 = `@ModelAttribute` (argument resolver 로 지정해둔 타입 외)
따라서 이 경우 `HelloData`에 `@RequestBody` 를 생략하면 `@ModelAttribute` 가 적용됨.<br>
`HelloData data` ->  `@ModelAttribute HelloData data`<br>
따라서 생략하면 HTTP 메시지 바디가 아니라 요청 파라미터를 처리하게 됨.


*requestBodyJsonV4 method - HttpEntity*
```java
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }
```
HttpEntity 도 사용 가능


*requestBodyJsonV5 method*
```java
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
```
`@ResponseBody`<br>
응답의 경우에도 @ResponseBody 를 사용하면 해당 객체를 HTTP 메시지 바디에 직접 넣어줄 수 있음.<br>
이 경우에도 HttpEntity 를 사용해도 됨.
- @RequestBody 요청
  - JSON 요청 HTTP 메시지 컨버터 객체
- @ResponseBody 응답
  - 객체 HTTP 메시지 컨버터 JSON 응답


---


## HTTP 응답 - 정적 리소스, 뷰 템플릿
스프링에서 응답 데이터를 만드는 방법은 크게 3가지임.

- 정적 리소스 
- 뷰 템플릿 <- 동적 리소스
- HTTP 메시지
  - HTTP API를 제공하는 경우 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON같은 형식으로 데이터를 실어 보냄.

  
### 정적 리소스 
스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공.
- `/static`
- `/public` 
- `/resources`
- `/META-INF/resources`

`src/main/resources`는 리소스를 보관하는 곳이고, 또 클래스패스의 시작 경로임.<br>
따라서 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공.


### 뷰 템플릿
- 뷰 템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달.
- 일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만, 다른 것들도 가능. 
뷰 템플릿이 만들 수 있는 것이라면 뭐든지 가능.
- 스프링 부트는 기본 뷰 템플릿 경로를 제공.

**뷰 템플릿 경로**<br>
`src/main/resources/templates`



*뷰 템플릿 생성*
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p th:text="${data}">empty</p>
</body>
</html>
```

*ResponseViewController - 뷰 템플릿을 호출하는 컨트롤러*
```java
package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("/response/hello")
                .addObject("data", "hello");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");
        return "/response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
```

*String을 반환하는 경우 - View or HTTP 메시지* <br>
- `@ResponseBody가 없을 때` : `response/hello`로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링.
- `@ResponseBody가 있을 때`:  뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접
`response/hello`라는 문자가 입력됨.

**Void를 반환하는 경우**
- `@Controller`를 사용하고, `HttpServletResponse`, `OutputStream(Writer)`같은
HTTP 메시지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용
  - 요청 URL: /response/hello
  - 실행: templates/response/hello.html
- 이 방식은 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않음.

**HTTP 메시지**
`@ResponseBody` , `HttpEntity` 를 사용하면, 뷰 템플릿을 사용하는 것이 아니라,
HTTP 메시지 바디에 직접 응답 데이터를 출력할 수 있음.


### Thymeleaf 스프링 부트 설정

스프링 부트가 자동으로 ThymeleafViewResolver 와 필요한 스프링 빈들을 등록한다. 그리고 다음 설정도 사용한다.
이 설정은 기본 값 이기 때문에 변경이 필요할 때만 설정하면 된다.

*application.properties*
```
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

---


## HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
HTTP API를 제공하는 경우 HTML이 아니라 데이터를 전달해야 하기 떄문에, HTTP 메시지 바디에 JSON 같은 데이터를 담아야 한다.


*ResponseBodyController*
```java
package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException
    {
        response.getWriter().write("ok");
    }
    
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
```

**responseBodyV1**
- 서블릿을 직접 다룰 때 처럼 `HttpServletResponse`객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달.<br>
`response.getWriter().write("ok")`

**responseBodyV2**
- `ResponseEntity`엔티티는 `HttpEntity`를 상속 받았는데, `HttpEntity`는
HTTP 메시지의 헤더, 바디 정보를 가지고 있음. `ResponseEntity`는 여기에 더해서 HTTP 응답 코드를 설정할 수 있음.<br>
`HttpStatus.CREATED` 로 변경하면 201 응답이 나가는 것을 확인할 수 있다.

**responseBodyV3**
- `@ResponseBody` 를 사용하면 view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력할 수 있음.
`ResponseEntity` 도 동일한 방식으로 동작한다.

**responseBodyJsonV1**
- `ResponseEntity`를 반환. HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환.

**responseBodyJsonV2**
- `ResponseEntity`는 HTTP 응답 코드를 설정할 수 있는데, `@ResponseBody`를 사용하면 이런 것을 설정하기 까다로우.
`@ResponseStatus(HttpStatus.OK)` 애노테이션을 사용하면 응답 코드도 지정 가능.

물론 애노테이션이기 때문에 응답 코드를 동적으로 변경할 수는 없음.
프로그램 조건에 따라서 동적으로 변경하려면 `ResponseEntity`를 사용.
 

**@RestController**<br>
`@Controller` 대신에 `@RestController`애노테이션을 사용하면, 해당 컨트롤러에 모두 `@ResponseBody`가 적용된다.
따라서 뷰 템플릿을 사용하는 것이 아니라, HTTP메시지 바디에 직접 데이터를 입력한다. 그대로 RestAPI를 만들 때 사용하는 컨트롤러이다.

> 참고 : `@RestController` 안에 `@ResponseBody`가 적용돼 있음.


--- 


## HTTP 메시지 컨버터

뷰 템플릿으로 HTML을 생성해 응답하는 것이 아닌, HTTP API처럼 JSON 데이터를
HTTP메시지 바디에서 직접 읽거나 쓰는 경우 HTTP 메시지 컨버터를 이용하면 편리하다.

**@ResponseBody 사용 원라**
![S6-1.png](img%2FS6-1.png)

- `@ResponseBody` 사용
  - HTTP의 Body에 문자 내용을 직접 반환
  - `viewResolver` 대신에 `HttpMessageConverter` 동작
  - 기본 문자처리 : `StringHttpMessageConverter`
  - 기본 객체처리 : `MappingJackson2HttpMessageConverter`
  - byte처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록돼 있음.


**스프링 MVC는 다음의 경우에 HTTP메시지 컨버터를 적용.**
- HTTP 요청 : `@RequestBody`, `HttpEntity(RequestEntity)`
- HTTP 응답 : `@ResponseBody`, `HttpEntity(ResponseEntity)`


*HTTP 메시지 컨버터 인터페이스*
```java
//생략
```
HTTP 메시지 컨버터는 HTTP요청, HTTP응답 둘 다 사용됨.
- `canRead()`, `canWrite()` : 메시지 컨버터가 해당 클래스, 미디어타입을 지원하는지 체크
- `read()`, `write()` : 메시지 컨버터를 통해 메시지를 읽고 쓰는 기능


**스프링 부트 기본 메시지 컨버터**
 ```
0 = ByteArrayHttpMessageConverter
1 = StringHttpMessageConverter
2 = MappingJackson2HttpMessageConverter
```
- ByteArrayHttpMessageConverter : `byte[]`데이터를 처리 
  - 클래스 타입 : `byte[]`, 미디어타입 : `*/*`
- `StringHttpMessageConverter` : `String`문자로 데이터 처리
  - 클래스 타입 : `String`, 미디어타입 : `*/*`
- `MappingJackson2HttpMessageConverter` : application/json
  - 클래스 타입 : 객체 또는 `HashMap`, 미디어타입 : `application/json` 관련


*StringHttpMessageConverter*
```
content-type: application/json
@RequestMapping
void hello(@RequestBody String data) {}
```

*MappingJackson2HttpMessageConverter*
```
content-type: application/json
@RequestMapping
void hello(@RequestBody HelloData data) {}
```

*?*
```
content-type: text/html
@RequestMapping
void hello(@RequestBody HelloData data) {}
```

**HTTP 요청 데이터 읽기**
- HTTP 요청이 오고, 컨트롤러에서 `@RequestBody`. `HttpEntity`파라미터를 사용
- 메시지 컨버터가 메시지를 읽을 수 있는지 확인하기 위해 `canRead()` 호출.
  - 대상 클래스 타입을 지원하는지
  - HTTP 요청의 Content-Type 미디어 타입을 지원하는지
- `canRead()`조건을 만족하면 `read()`를 호출해 객체 생성, 반환함.


**HTTP 응답 데이터 생성**
- 컨트롤러에서 `@ResponseBody`, `HttpEntity`로 값이 반환.
- 메시지 컨버터가 메시지를 쓸 수 있는지 확인하기 위해 `canWrite()`를 호출.
  - 대상 클래스 타입을 지원하는지 
  - HTTP 요청의 Accept 미디어 타입을 지원하는지
- `canWrite()`조건을 만족하면 `write()`를 호출해 HTTP응답 메시지 바디에 데이터를 생성.


---


## 요청 메핑 핸들러 어댑터 구조

HTTP 메시지 컨버터는 스프링 MVC에서 애노테이션 기반의 컨트롤러, 즉 `@RequestMapping`을 처리하는 
핸들러 어댑터인 `RequestMappingHandlerAdpater`(요청 메핑 핸들러)에 있다.


**RequestMappingHandlerAdapter 동작 방식**
![S6-2.png](img%2FS6-2.png)

**ArgumentResolver**<br>
애노테이션 기반의 컨트롤러는 매우 다양한 파라미터를 사용할 수 있음.
`HttpServletRequest`, `Model`은 물론이고, `@RequsetParam`, `@ModelAttribute` 같은 애노테이션
그리고 `@RequestBody`, `HttpEntity`같은 HTTP 메시지를 처리하는 부분까지 매우 큰 유연함을 보여줌.<br>
파라미터를 유연하게 처리할 수 있는 이유가 바로 `ArgumentResolver` 덕분임.

애노테이션 기반 컨트롤러를 처리하는 `RequestMappingHandlerAdapter`는 바로 이 `ArgumentResolver`를 호출해 
컨트롤러(핸들러)가 필요로하는 다양한 파라미터 값(객체)를 생성함. 그리고 이렇게 파라미터의 값이 모두 준비되면
컨트롤러를 호출해 값을 넘겨줌


**ReturnValueHandler**
`HandlerMethodReturnValueHandler`를 줄여서 `ReturnValueHandler`라 부름<br>
`ArgumentResolver`와 비슷한데, 이것은 응답 값을 변환하고 처리.<br>
컨트롤러에서 `String`으로 뷰 이름을 반환해도, 동작하는 이유가 바로 `ReturnValueHandler`때문임.

스프링은 10여개가 넘는 `ReturnValueHandler` 를 지원.<br>
예) `ModelAndView` , `@ResponseBody` , `HttpEntity` , `String`


### HTTP 메시지 컨버터

**HTTP 메시지 컨버터 위치**
![S6-3.png](img%2FS6-3.png)

HTTP 메시지 컨버터를 사용하는 `@RequestBody`도 컨트롤러가 필요로 하는 파라미터의 값에 사용.<br>
`@RequestBody`의 경우 컨트롤러의 반환 값을 이용.


**요청의 경우**
- `@RequestBody`를 처리하는 `ArgumentResolver`가 있고, `HttpEntity`를 처리하는`ArgumentResolver`가 있음.
이 `ArgumentResolver` 들이 HTTP 메시지 컨버터를 사용해서 필요한 객체를 생성


**응답의 경우**
- `@ResponseBody`와 `HttpEntity`를 처리하는 `ReturnValueHandler`가 있음. 
여기에서 HTTP 메시지 컨버터를 호출해서 응답 결과를 만듦

스프링 MVC는 `@RequestBody`, `@ResponseBody`가 있으면 `RequestResponseBodyMethodProcessor()`를,
`HttpEntity`가 있으면 `HttpEntityMethodProcessor()`를 사용









---
