# 서블릿


## Hello 서블릿

> **참고** <br>
> 서블릿은 톰캣 같은 WAS 서버를 직접 설치, 그 위에 서블리 ㅅ코드를 클래스 파일로 빌드해서
> 올린 다음, 톰캣 서버를 실행한다. 이 과정은 매우 번거롭다. <br>
> 스프링 부트는 톰캣 서버를 내장하고 있으므로, 톰캣 서버 설치 없이 편리하게 서블릿 코드를 실행할 수 있다.


### 스프링 부트 서블릿 환경 구성

`@ServletComponentScan`<br>
스프링 부트는 서블릿을 직접 등록해서 사용할 수 있도록 `@ServletComponentScan`을 지원한다.
다음과 같이 추가한다.

*ServletApplication*
```java
package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
```
<br>

**서블릿 등록하기** 

*HelloServlet*
```java
package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("requset = " + requset);
        System.out.println("response = " + response);

        String username = requset.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
```

- `@WebServlet` 서블릿 애노테이션
  - name : 서블릿 이름
  - urlPatterns : URL매핑

HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 다음 메서드를 실행<br>
`protected void service(HttpServletRequest requset, HttpServletResponse response)`

- 웹 브라우저 실행
  - http://localhost8080/hello?username=world
  - 결과 : hello world

- 콘솔 실행결과
```
HelloServlet.service
requset = org.apache.catalina.connector.RequestFacade@42a1a9b6
response = org.apache.catalina.connector.ResponseFacade@e7a3bb
username = world
```


### 서블릿 컨테이너 동작 방식
**내장 톰캣 서버 생성**
![S2-1.png](img%2FS2-1.png)

**HTTP 요청, HTTP응답 메시지**
![S2-2.png](img%2FS2-2.png)

**웹 애플리케이션 서버의 요청 응답 구조**
![S2-3.png](img%2FS2-3.png)

> **참고** <br>
> HTTP응답에서 Content-Length는 웹 애플리케이션 서버가 자동으로 생성해준다.


### welcome 페이지 추가

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
    <li><a href="basic.html">서블릿 basic</a></li>
</ul>
</body>
</html>
```


## HttpServletRequest - 개요

**HttpServletRequest 역할** <br>
HTTP 요청 메시지를 개발자가 직접 파싱해도 사용해도 되지만, 매우 불편하다. 서블릿은 개발자가 HTTP 요청 메시지를 
편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그리고 그 결과를 `HttpServletRequest`객체에 담아서 제공한다.

HttpServletRequest를 사용하면 다음과 같은 HTTP 요청 메시지를 편리하게 조회할 수 있다.

*HTTP 요청 메시지*
```
POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
username=kim&age=20
```

- START LINE
  - HTTP 메서드
  - URL
  - 쿼리 스트링
  - 스키마, 프로토콜
- 헤더
  - 헤더 조회
- 바디
  - form 파라미터 형식 조회
  - message body 데이터 직접 조회

HttpServletRequest 객체는 추가로 여러가지 부가기능도 함께 제공한다.

**임시 저장소 기능**
- 해당 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능 
  - 저장 : `request.setAttribute(name, value)`
  - 조회 : `request.getAttribute(name)`

**세션 관리 기능**
- `request.getSession(create: true)`

> **중요**<br>
> HttpServletRequest, HttpServletResponse를 사용할 때 가장 중요한 점은 이 객체들이 HTTP 요청 메시지,
> HTTP 응답 메시지를 편리하게 사용하도록 도와주는 객체라는 점이다. 따라서 이 기능에 대해 깊이 이해하려면
> **HTTP 스팩이 제공하는 요청, 응답 메시지 자체를 이해**해야 한다.



## HttpServletRequest - 기본 사용법
HttpServletRequest가 제공하는 기본 기능

*RequestHeaderServlet*
```java
package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);

    }

    //start line 정보
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " +
                request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        /*
         Enumeration<String> headerNames = request.getHeaderNames();
         while (headerNames.hasMoreElements()) {
         String headerName = headerNames.nextElement();
         System.out.println(headerName + ": " + request.getHeader(headerName));
         }
        */
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": "
                        + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }


    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
```
실행결과
```
--- REQUEST-LINE - start ---
request.getMethod() = GET
request.getProtocol() = HTTP/1.1
request.getScheme() = http
request.getRequestURL() = http://localhost:8080/request-header
request.getRequestURI() = /request-header
request.getQueryString() = null
request.isSecure() = false
--- REQUEST-LINE - end ---

--- Headers - start ---
host: localhost:8080
connection: keep-alive
cache-control: max-age=0
sec-ch-ua: "Not_A Brand";v="8", "Chromium";v="120", "Google Chrome";v="120"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
upgrade-insecure-requests: 1
user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36
accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
sec-fetch-site: same-origin
sec-fetch-mode: navigate
sec-fetch-user: ?1
sec-fetch-dest: document
referer: http://localhost:8080/basic.html
accept-encoding: gzip, deflate, br
accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
cookie: Idea-1afd2dea=715e6892-4a57-4307-afc0-f323498e55fb
--- Headers - end ---

--- Header 편의 조회 start ---
[Host 편의 조회]
request.getServerName() = localhost
request.getServerPort() = 8080

[Accept-Language 편의 조회]
locale = ko_KR
locale = ko
locale = en_US
locale = en
request.getLocale() = ko_KR

[cookie 편의 조회]
Idea-1afd2dea: 715e6892-4a57-4307-afc0-f323498e55fb

[Content 편의 조회]
request.getContentType() = null
request.getContentLength() = -1
request.getCharacterEncoding() = UTF-8
--- 기타 조회 start ---
[Remote 정보]
request.getRemoteHost() = 0:0:0:0:0:0:0:1
request.getRemoteAddr() = 0:0:0:0:0:0:0:1
request.getRemotePort() = 57354

[Local 정보]
request.getLocalName() = 0:0:0:0:0:0:0:1
request.getLocalAddr() = 0:0:0:0:0:0:0:1
request.getLocalPort() = 8080
--- 기타 조회 end ---
```

> **참고**<br>
> 로컬에서 테스트하면 IPv6정보가 나오는데, IPv4 정보를 보고싶다면 다음 옵션을 VM options에 넣어주면 된다.
> <br> `-Djava.net.preferIPv4Stack=true`


## HTTP 요청 데이터 - 개요
HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법이 있다.

**주로 다음 3가지 방법을 사용**
- **GET - 쿼리 파라미터**
  - /url?username=seob&age=24
  - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
  - 예)검색, 필터, 페이징등등에서 많이 사용하는 방식
- **POST - HTML Form**
  - content-type : application/x-www-form-urlencoded
  - 메시지 바디에 쿼리 파라미터 형식으로 전달 username=seob&age=24
  - 예)회원 가입, 상품 주문, HTML Form 사용
- **HTTP message body**에 데이터를 직접 담아서 요청
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - 데이터 형식은 주로 JSON에 사용 
  - POST, PUT, PATCH


### HTTP 요청 데이터 - GET 쿼리 파라미터

전달 데이터
- username=hello
- age=20

메시지 바디 없이, URL의 **쿼리 파라미터**를 사용해서 데이터 전달
- 예)검색, 필터, 페이징등등에서 많이 사용하는 방식

쿼리 파라미터는 URL에 다음과 같이 `?`를 시작으로 보낼 수 있다. 추가 파라미터는 `&`로 구분
- http://localhost:8080/request-param?username=hello&age=20

*RequestParamServlet*
```java
package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

//        Enumeration<String> parameterNames = requset.getParameterNames();
        requset.getParameterNames().asIterator().forEachRemaining(
                paramName -> System.out.println(paramName + "=" + requset.getParameter(paramName))
        );

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username= requset.getParameter("username");
        String age = requset.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = requset.getParameterValues("username");
        for(String name: usernames) {
            System.out.println("username = " + name);
        }
    }
}
```
실행결과 - 파라미터 전송<br>
http://localhost:8080/request-param?username=hello&age=20
```
[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end

[단일 파라미터 조회]
username = hello 
age = 20
[이름이 같은 복수 파라미터 조회]
username = hello
```
<br>

실행결과 - 동일 파라미터 전송
```
[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end
[단일 파라미터 조회]
request.getParameter(username) = hello
request.getParameter(age) = 20
[이름이 같은 복수 파라미터 조회]
request.getParameterValues(username)
username=hello
username=Lee
```

파라미터의 값은 하나인데, 값이 중복이면 `request.getParameterValue()` 를 사용해야한다.<br>
중복일 때 `request.getParameter()`를 사용하면 `request.getParameterValue()`의 첫 번째 값을 반환한다.


## HTTP 요청 데이터 - POST HTML FORM
주로 회원가입, 상품주문 등에서 사용

**특징**
- content-type : `application/x-www-form-urlencode`
- 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전달. `username=hello&age=20`

*hello-form.html*
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/request-param" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```

실행결과

![S2-4.png](img%2FS2-4.png)

전송버튼 클릭

![S2-5.png](img%2FS2-5.png)

POST의 HTML FORM을 전송하면 웹 브라우저는 다음 형식으로 HTTP 메시지를 만든다.
- **요청 URL** : http://localhost:8080/request-param
- **content-type** : `application/x-www-form-urlencoded`
- **message body** : `username=hello&age=20`

`application/x-www-form-urlencoded`형식은 앞서 GET에서 살펴본 쿼리 파라미터 형식과 같다. 
따라서 **쿼리 파라미터 조회 메서드를 그대로 사용**하면 된다.

`request.getParameter()`는 GET URL 쿼리 파라미터 형식도 지원, POST HTML FORM 형식도 지원한다.

> **참고** <br>
> content-type은 HTTP메시지 바디에 데이터 형식을 지정. <br>
> **GET URL 쿼리 파라미터 형식**으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP 메시지 바디를 사용하지 않기 때문에 content-type이 없다.<br>
> **POST FHTML Form 형식**으로 데이터를 전달하면 HTTP 메시지 바디에 해당 데이터를 포함해서 보내기 때문에 바디에 포함된
> 데이터가 어떤 형식인지 content-type을 반드시 지정해야 한다. 이렇게 폼으로 데이터를 전송하는 형식을 `application/x-www-form-urlencoded`라 한다.


### Postman을 사용한 테스트
Postman을 사용하면 굳이 HTML form 을 생성하지 않고 테스트 가능하다.
![S2-6.png](img%2FS2-6.png)


## HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
- **HTTP message body**에 데이터를 직접 담아서 요청
  - HTTP API에서 주로 사용, JSON, XML, TEXT
  - 데이터 형식은 주로 JSON사용
  - POST, PUT, PATCH


- 먼저 가장 단순한 텍스트 메시지를 HTTP 메시지에 담아 전송하고 읽기.
- HTTP 메시지 바디의 데이터를 InputStream을 사용해 직접 읽을 수 있다.

*RequestBodyStringServlet*
```java
package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="RequestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    System.out.println("messageBody = " + messageBody);

    response.getWriter().write("OK");
  }
}
```

Postman 테스트

![S2-7.png](img%2FS2-7.png)

문자 전송
- POST http://localhost:8080/request-body-string
- content-type: text/plain
- message body: hello

실행결과
```
messageBody = helloooo!
```

> **참고**<br>
> inputStream은 byte코드로 반환. byte코드를 우리가 읽을 수 있는 문자(String)로 보려면
> 문자표(Charset)를 지정해야함. 여기서 UTF_8을 지정



## HTTP 요청 데이터 - API 메시지 바디 - JSON
HTTP API에서 주로 사용하는 JSON형식으로 데이터 전달해보기

**JSON 형식 전송**
- content-type : **application/json**
- message body : `{"username": "hello","age": 20}`
- 결과 : `messageBody = {"username":"hello","age":20}`

**JSON 형식 바싱 추가**

*HelloData*
```java
package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {
  private String username;
  private int age;
}
```

*RequestBodyJsonServlet*
```java
package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}

```
Postman으로 실행

![S2-8.png](img%2FS2-8.png)

실행결과
```
messageBody = {"username": "hello","age": 20}
helloData.username = hello
helloData.age = 20
```

> **참고** <br>
> JSON결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 JSON 변환 라이브러리를 추가해서 사용해야한다.
> Spring MVC를 선택하면 기본으로 Jackson 라이브러리(ObjectMapper)를 함께 제공한다.


> **참고** <br>
> HTML form 데이터도 메시지 바디를 통해 전송되므로 직접 읽을 수 있다. 하지만 편리한 파라미터 조회기능
> (request.getParameter(...))을 이미 제공하기 때문에 파라미터 조회 기능을 사용하면 된다.



## HttpServletResponse - 기본 사용법
### HttpServletResponse 역할

**HTTP 응답 메시지 생성**
- HTTP 응답 코드 지정
- 헤더 생성
- 바디 생성 

**편의 기능 제공**
- Content-Type, 쿠키, Redirect


### HttpServletResponse - 기본 사용법

*ResponseHeaderServlet*
```java
package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
//        content(response);
//        cookie(response);
//        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
}
```

*Content 편의 메서드*
```java
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }
```

*Cookie 편의 메서드*
```java
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
```

*Redirect 편의 메서드*
```java
   private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
```


## HTTP 응답 데이터 - 단순 텍스트, HTML
HTTP 응답 메시지는 주로 아래와 같은 내용을 담아 전달
- 단순 텍스트 응답
  - `writer.println("ok");`
- HTML 응답
- HTML API - MessageBody JSON 응답

### HttpServletResponse - HTML 응답

*ResponseHtmlServlet*
```java
package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: text/html;charset=utf-8
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println(" <div>HI?</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
```

HTTP 응답으로 HTML을 반환할 때 content-type을 `text/html`로 지정해야 한다.


## HTTP 응답 데이터 - API JSON


*ResponseJsonServlet*
```java
package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("lee");
        helloData.setAge(20);

        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
```

HTTP 응답으로 JSON을 반환할 때 content-type을 `application/json`로 지정해야 한다.<br>
Jackson 라이브러리가 제공하는 `objectMapper.writeValueAsString()`를 사용하면 객체를 JSON 문자로 변경할 수 있다.


