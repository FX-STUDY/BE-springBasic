# 3. 서블릿, JSP, MVC 패턴 


## 회원 관리 웹 애플리케이션 요구사항

회원정보 - 이름, 나이<br>
기능 요구사항 - 회원 저장, 회원 목록 조회

*Member*
```java
package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
```


*MemberRepository*
```java
package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository(){}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
```

싱글톤 패턴 적용. 싱글톤 패턴은 객체를 단 하나만 생성해서 공유해야 하므로 생성자를 private 접근자로 막아둔다.


*MemberRepositoryTest*
```java
package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("Hello", 20);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);

    }

    @Test
    void findAll() {
        Member member1 = new Member("Hello1", 20);
        Member member2 = new Member("Hello2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        Assertions.assertThat(memberList.size()).isEqualTo(2);
        Assertions.assertThat(memberList).contains(member1, member2);
    }


}
```


## 서블릿으로 회원 관리 웹 애플리케이션 만들기

*MemberFormServlet - 회원 등록 폼*
```java
package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");    }
}
```


*MemberSaveServlet - 회원 저장*
```java
package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();

        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
```

- `MemberSaveServlet` 동작 순서
  1. 파라미터를 조회해 Member 객체 생성
  2. Member 객체를 MemberRepository를 통해 저장.
  3. Member 객체를 사용해 결과 화면용 HTML을 동적으로 만들어 응답.



*MemberListServlet - 회원 목록*
```java
package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

   private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");
/*
 w.write(" <tr>");
 w.write(" <td>1</td>");
 w.write(" <td>userA</td>");
 w.write(" <td>10</td>");
 w.write(" </tr>");
*/
        for (Member member : members) {
            w.write(" <tr>");
            w.write(" <td>" + member.getId() + "</td>");
            w.write(" <td>" + member.getUsername() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
```

- `MemberListServlet` 동작 순서
  1. `memberRepository.findAll()`을 통해 모든 회원을 조회.
  2. 회원 목록 HTML을 for 루프를 통해 회원 수 만큼 동적으로 생성하고 응답.



### Welcome 페이지 변경

*index.html*
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
    <li>서블릿
        <ul>
            <li><a href="/servlet/members/new-form">회원가입</a></li>
            <li><a href="/servlet/members">회원목록</a></li>
        </ul>
    </li>
    <li>JSP
        <ul>
            <li><a href="/jsp/members/new-form.jsp">회원가입</a></li>
            <li><a href="/jsp/members.jsp">회원목록</a></li>
        </ul>
    </li>
    <li>서블릿 MVC
        <ul>
            <li><a href="/servlet-mvc/members/new-form">회원가입</a></li>
            <li><a href="/servlet-mvc/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v1
        <ul>
            <li><a href="/front-controller/v1/members/new-form">회원가입</a></li>
            <li><a href="/front-controller/v1/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v2
        <ul>
            <li><a href="/front-controller/v2/members/new-form">회원가입</a></li>
            <li><a href="/front-controller/v2/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v3
        <ul>
            <li><a href="/front-controller/v3/members/new-form">회원가입</a></li>
            <li><a href="/front-controller/v3/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v4
        <ul>
            <li><a href="/front-controller/v4/members/new-form">회원가입</a></li>
            <li><a href="/front-controller/v4/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v5 - v3
        <ul>
            <li><a href="/front-controller/v5/v3/members/new-form">회원가입</a></
            li>
            <li><a href="/front-controller/v5/v3/members">회원목록</a></li>
        </ul>
    </li>
    <li>FrontController - v5 - v4
        <ul>
            <li><a href="/front-controller/v5/v4/members/new-form">회원가입</a></
            li>
            <li><a href="/front-controller/v5/v4/members">회원목록</a></li>
        </ul>
    </li>
    <li>SpringMVC - v1
        <ul>
            <li><a href="/springmvc/v1/members/new-form">회원가입</a></li>
            <li><a href="/springmvc/v1/members">회원목록</a></li>
        </ul>
    </li>
    <li>SpringMVC - v2
        <ul>
            <li><a href="/springmvc/v2/members/new-form">회원가입</a></li>
            <li><a href="/springmvc/v2/members">회원목록</a></li>
        </ul>
    </li>
    <li>SpringMVC - v3
        <ul>
            <li><a href="/springmvc/v3/members/new-form">회원가입</a></li>
            <li><a href="/springmvc/v3/members">회원목록</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
```




## JSP로 회원 관리 웹 애플리케이션 만들기

### JSP 라이브러리 추가

*build.gradle*
```groovy
	//JSP 추가 시작
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
implementation 'jakarta.servlet:jakarta.servlet-api' //스프링부트 3.0 이상
implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api' //스프링부트3.0 이상
implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl' //스프링부트 3.0 이상
//JSP 추가 끝
```

*new-form.jsp - 회원 등록 폼*
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```

- `<%@ page contentType="text/html;charset=UTF-8" language="java" %>`
  - 첫 줄은 JSP 문서라는 뜻.
 
*save.jsp - 회원 저장*
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    //request, response는 지원함
    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
```

- '<%@ page import= %>'
  - 자바의 import 문과 동일.
- '<% %>'
  - 이 부분에 자바 코드 입력 가능.
- `<%= %>`
  - 이 부분에 자바 코드 출력 가능.

회원 저장 JSP는 회원 저장 servlet 코드와 같다. 다른 점은, HTML을 중심으로 하고,
자바 코드를 부분부분 입력해 주었다는 점이다. `<% %>`를 사용해 HTML 중간에 자바 코드를 출력하고 있다.

*members.jsp - 회원 목록* 
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write(" <tr>");
            out.write(" <td>" + member.getId() + "</td>");
            out.write(" <td>" + member.getUsername() + "</td>");
            out.write(" <td>" + member.getAge() + "</td>");
            out.write(" </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
```

회원 repository를 먼저 조회, 결과 List를 사용해 중간에 `<tr><td>`HTML 태그를 반복해서 출력하고 있다.


### 서블릿과 JSP의 한계
서블릿으로 개발할 때는 뷰(view)화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여 복잡했다.<br>
JSP를 사용한 덕분에 뷰를 생성하는 HTML 작업을 깔끔하게 정리하고, 중간중간 동적으로 변경이 필요한 부분에만
자바 코드를 적용했다. 하지만 몇 가지 문제점이 존재한다.

회원 저장 폼에서 코드의 상위 절반은 회원을 저장하는 비지니스 로직, 나머지 절반은 결과를 보여주는 HTML 뷰 영역이다. 회원 목록도 마찬가지이다. <br>
JAVA코드, 데이터를 조회하는 repository 등등 다양한 코드가 모두 JSP에 노출돼 있다. 만약 수백, 수천줄이 넘어간다면 유지보수에 큰 어려움이 발생한다.

<br>
**MVC 패턴 등장**
<br>비지니스 로직은 서블릿 처럼 다른곳에서 처리하고, JSP는 목적에 맞게 HTML로 화면(VIEW)을 보여주는 일에 집중하도록 하게 해준다.




## MVC 패턴 -적용
서블릿을 컨트롤러로 사용하고, JSP를 뷰로 사용해 MVC 패턴 적용
<br>Model은 HttpServletRequest 객체 사용. request는 내부에 데이터 저장소를 가지고 있는데,
'request.setAttribute()', `request.getAttribute`를 사용하면 데이터를 보관하고, 조회할 수 있다.


### 회원 등록

*MvcMemberFormServlet - 회원 등록 폼 컨트롤러*
```java
package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
```

`dispatcher.forward()` : 다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출 발생

> `/WEB-INF`<br>
> 이 경로 안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 즉 항상 컨트롤러를 통해서 JSP를 호출하는 것이다.

> **redirect vs forward** <br>
> 리다이렉트는 실제 클라이언트(web)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다.
> 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. 반면에 포워드는 서버 내부에서 
> 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.


*new-form.jsp - 회원 등록 폼 뷰*
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```

여기서 form의 action이 상대경로로 지정돼 있다. 이렇게 상대경로로 지정하면 폼 전송시 
현재 URL이 속한 계층 경로 + save가 호출된다.<br>
현재 계층 경로 : `/servlet-mvc/members/`<br>
결과 : `/servlet-mvc/members/save`


### 회원 저장 

*MvcMemberSaveServlet - 회원 저장 컨트롤러*
```java
package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);


        //Model에 데이터 보관.
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
```
HttpServletRequest를 Model로 사용.<br>
request가 제공하는 setAttribute()를 사용하면 request 객체에 데이터를 보관해 뷰에 전달할 수 있다.<br>
뷰는 request.getAttribute()를 사용해 데이터를 꺼내면 된다.


*save-result - 회원저장 뷰*
```html 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
```

JSP는 `${}` 문법을 제공. 이 문법을 사용하면 request의 attribute에 담긴 데이터를 편리하게 조회할 수 있다.


### 회원 목록 조회 

*MvcMemberListServlet - 회원 목록 조회 컨트롤러*
```java
package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
```

request 객체를 사용해 `List<Member> members`를 모델에 보관한다.


*members - 회원 목록 조회 뷰*
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
```
모델에 담아둔 members를 JSP가 제공하는 taglib기능을 사용해 반복해서 출력한다.
`memebers`리스트에서 `members`를 순서대로 꺼내 `item`변수에 담고, 출력하는 과정을 반복한다.
<br><br>
`<c:forEach>`이 기능을 사용하려면 다음과 같이 선언해야 한다.<br>
`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>`



## MVC 패턴 - 한계
MVC 패턴을 적용한 덕분에 컨트롤러의 역할과 뷰를 렌더링 하는 역할을 명확하게 구분할 수 있다.<br>
뷰는 화면을 그리는 역할에 충실한 덕분에, 코드가 깔끔하고 직관적이다. 단순하게 모델에서 필요한 데이터를 수집하고, 화면을 만들면 된다.
<br>
하지만 컨트롤러는 중복코드가 많고, 필요하지 않은 코드가 많이 존재한다.


**MVC 컨트롤러의 단점**<br>

*foward 중복*<br>
view로 이동하는 코드가 항상 중복 호출된다.
```
RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
dispatcher.forward(request, response);
```


**ViewPath 중복**
```java
String viewPath = "/WEB-INF/views/save-result.jsp";
```

- prefix : `/WEB-INF/views/`
- suffix : `.jsp`<br>
그리고 만약 jsp가 아닌 thymeleaf같은 뷰로 변경한다면 전체 코드를 다 변경 작업을 해야 한다.


**사용하지 않는 코드**<br>
다음 코드를 사용할 때도 있고, 사용하지 않을 때도 있다. 
```
HttpServletRequest request, HttpServletResponse response
```
그리고 이런 `HttpServletRequest`, `HttpServletResponse`를 사용하는 코드는 테스트 케이스를 작성하기 어렵다.


**공통 처리 어려움**<br>
기능이 복잡해질수록 컨트롤러에서 공통으로 처리해야 하는 부분이 점점 더 많이 증가한다. 단순히 공통 기능을 메서드로 
생성하면 될 것 같지만, 결과적으로 해당 메서드를 항상 호출해야 하고, 실수로 호출하지 않으면 문제가 될 수도 있다. 그리고 호출하는 것 자체도 중복이다.


**정리!**<br>
이 문제를 해결하려면 컨트롤러 호출 전에 먼저 공통 기능을 처리해야 한다. 소위 **수문장 역할**을 하는 기능이 필요하다.
**Front Controller**패턴을 도입하면 이런 문제를 깔끔하게 해결할 수 있다.(입구를 하나로)<br>
스프링 MVC의 핵심도 바로 이 프론트 컨트롤러에 있다.

















