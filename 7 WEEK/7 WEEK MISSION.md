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
이번주 부터 제공드린 Spring Boot 강의를 토대로 개인 공부가 시작 됩니다.<br>
그리고 GIVE 프로젝트도 시작될 예정입니다. <br>

일단 이번주 개인 공부는<br>
spring 핵심원리 기본편은 학교에서 대부분 진행한 내용으로 공부하는데 문제가 없을 것 입니다. <br>
1 ~ 7 장은 리마인드로 공부 하시고 [ read me 에 요약할 필요 없음 ] <br>
8,9 정말 중요한 내용이므로 집중해서 공부 바랍니다. [ read me 요약 필요 ] <br>

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

8.빈 생명주기 콜백 시작

db connection pool, network socket 등 시작 지점에 연결, 어플 종료 시점에 연결 해제하려면 미리 객체의 초기화,종료 작업이 필요.<br>
ex) 외부 네트워크의 미리 연결하는 객체 생성 시 시작 지점에 connect()를 호출하여 연결, 종료 시점에 disconnect(),close() 호출하여 연결 해제한다.<br>

스프링 빈 라이프사이클 : 객체생성 -> 의존관계 주입 (생성자 예외) <br>
스프링 빈은 객체를 생성, 의존관계 주입이 모두 끝난 후 사용 가능 -> 초기화 작업은 의존관계 주입이 모두 완료되고 난 이후 호출해야한다. <br>
스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 기능을 제공, 또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백 제공 -> 안전하게 종료 작업 진행 가능 <br>

스프링 빈의 이벤트 라이프사이클 : <br>
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료 <br>

"참고"-> 생성자와 초기화를 분리하여야한다(책임을 분리해야 하고, 유지보수가 용이해진다.) <br>
스프링의 3가지 빈 생명주기 콜백 : <br>
인터페이스, 설정 정보에 초기화 메서드, 종료 메서드 지정, @PostConstruct, @PreDestroy <br>

1.초기화,소멸 인터페이스 단점: 이 인터페이스는 스프링 전용 인터페이스로, 해당 코드가 스프링 전용 인터페이스에 의존한다. <br>
초기화, 소멸 메서드의 이름 변경 불가능 <br>
내가 코드를 고칠 수 없는 외부 라이브러리에 적용 불가능. <br>

2.빈 등록 초기화, 소멸 메서드(@Bean(initMethod= "init메서드이름", destroyMethod="close메서드이름")) <br>
장점 : 메서드 이름을 자유롭게 지정 가능, 스프링 빈이 스프링 코드에 의존x, 코드가 아니라 설정 정보를 사용하여 코드를 고칠 수 없는 외부 라이브러리에서도 사용 가능 <br>
@Bean destroyMethod는 default가 (inferred)로 등록되어 있어 close, shutdown 이름의 메서드를 자동으로 호출한다. 해당 기능을 사용하고싶지 않을때는 destroyMethod=""을 지정한다. <br>

3.@PostConstruct, @PreDestroy 사용 <br>
init 메서드에 @PostConstruct, close 메서드에 @PreDestroy 어노테이션 사용 <br>
장점 : 최신 스프링에서 가장 권장하는 방법, 사용방법 편리, 스프링이 아닌 다른 컨테이너에서도 동작, 컴포넌트 스캔과 잘 어울린다. <br>
단점 : 외부 라이브러리에 적용 x, 외부 라이브러리를 초기화 할때는 @Bean 기능 사용 <br>

9.빈 스코프

빈 스코프 : 빈이 존재할 수 있는 범위, 기본적으로 싱글톤 스코프로 생성된다. <br>

싱글톤 : 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프 <br>
프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관여하지 않는 매우 짧은 범위의 스코프 <br>
request : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프 <br>
session : 웹 세션이 생셩되고 종료될 때 까지 유지되는 스코프 <br>
application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프 <br>

컴포넌트 스캔 자동등록 : @Component 위에 @Scope("prototype") 사용 <br>
수동 등록 : @Bean 위에 @Scope("prototype") 사용 <br>

싱글톤 빈 요청 : 늘 같은 인스턴스의 스프링 빈 반환. <br>
프로토타입 빈 요청 : 늘 새로운 프로토타입 빈을 생성하여 의존관계를 주입한다. <br>
프로토타입에 경우 컨테이너가 생성,의존관계 주입,초기화 까지만 담당, 즉 종료 메서드 같은 경우 컨테이너에서 @PreDestroy같은 어노테이션이 적용되지 않아 클라이언트가 직접 종료해야한다. <br>
싱글톤과 프로토타입 빈은 같이 쓰면 오류가 발생 할 수 있다 -> <br>
싱글톤 내부에서 의존관계로 프로토타입 빈을 자동주입 할 때 싱글톤 내부에서 프로토타입 빈을 내부 필드에 보관하여 원하는 대로 작동하지 않는다, 즉 프로토타입 빈이 호출할때마다 생성되지 않는다. <br>

프로토타입 스코프 싱글톤 빈과 함께 사용 시 provider로 해결 -> <br>
ObjectProvider 클래스에 getObject() 메서드를 사용 시 항상 새로운 프로토타입 빈이 생성된다. <br>

웹 스코프 : 웹 환경에서만 동작, 스프링이 해당 스코프의 종료시점까지 관리, 즉 종료 메서드가 호출됨. <br>

웹 스코프 종류 : <br>
request : Http 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, Http 요청마다 객체 생성, 관리됨 <br>
session : Http session과 동일한 생명주기를 가짐 <br>
application : Servlet Context와 동일한 생명주기를 가짐 <br>
websocket : 웹 소켓과 동일한 생명주기를 가짐 <br>

@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS) <br>
적용 대상이 클래스면 TARGET_CLASS, 인터페이스면 INTERFACES 선택 <br>
가짜 프록시 클래스를 생성, request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입 가능. <br>
가짜 프록시 객체는 요청 시 내부에서 진짜 빈을 요청하는 위임 로직 존재. <br>
-> 싱글톤 빈을 사용하듯이 편리하게 request 사용 가능, 진짜 객체 조회를 필요한 시점까지 지연처리 <br>

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
