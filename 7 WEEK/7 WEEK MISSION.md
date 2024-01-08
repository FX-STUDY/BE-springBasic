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
<h1>스프링 핵심 원리 기본편 8-9강 요약</h1>
<br>
<h2>빈 생명주기 콜백 시작</h2><br>
데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요.<br>
<br>
객체를 생성하는 단계에는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주입을 통해서 setUrl()이 호출되어야 url이 존재하게됨.<br>
<br>
<h2>스프링 빈 라이프사이클</h2><br>
객체 생성 -> 의존관계 주입<br>
스프링은 의존관계 주입이 완려되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공하고 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 줌.<br>
<br>
<h2>스프링 빈의 이벤트 라이프사이클</h2><br>
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료<br>
- 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출<br>
- 소멸전 콜백: 빈이 소멸되기 직전에 호출<br>
<br>
! 객체의 생성과 초기화를 분리<br>
: 생성자 안에서 초기화 작업을 함께 하는 것 보다 분리하여 명확하게 나누는 것이 유지보수 관점에서 좋음.<br>
<br>
! 싱글톤 빈들은 스프링 컨테이너가 종료될 때 함께 종료되기 때문에 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어남.<br>
<br>
<h2>스프링의 빈 생명주기 콜백 지원</h2><br>
1. 인터페이스<br>
- InitializingBean은 afterPropertiesset() 메서드로 초기화 지원<br>
- DisposableBean은 destroy() 메서드로 소멸 지원<br>
- 초기화, 소멸 인터페이스 단점:<br>
 스프링 전용 인터페이스이기 때문에 코드가 스프링 전용 인터페이스에 의존,<br>
 초기화, 소멸 메서드의 이름 변경 불가,<br>
 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없음<br>
<br>
2. 설정 정보에 초기화 메서드, 종료 메서드 지정<br>
- 설정 정보에 @Bean(initMethod=“init”, destroyMethod=“close”)와 같이 초기화, 소멸 메서드를 지정할 수 있음.<br>
- 설정 정보 사용 특징:<br>
 메서드 이름을 자유롭게 지정 가능,<br>
 스프링 빈이 스프링 코드에 의존 하지않음,<br>
 외부 라이브러리에도 초기화, 종료 메서드를 적용 가능<br>
- 종료 메서드 추론<br>
 @Bean의 destroyMethod는 기본값이 inferred로 등록되어있음,<br>
 inferred는 close, shutdown라는 이름의 메서드를 자동으로 호출,<br>
 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작함,<br>
 inferred 기능을 사용하기 싫으면 destoryMedthod=“”로 빈 공백을 지정해주면 됨<br>
<br>
3. @PostConstruct, @PreDestroy 어노테이션 지원<br>
- 특징:<br>
 매우 편리함, 최신 스프링에서 가장 권장하는 방법,<br>
 스프링에 종속적인 기술이 아니라 스프링이 아닌 다른 컨테이너에서도 동작,<br>
 컴포턴트 스캔과 잘 어울림,<br>
- 단점:<br>
 외부 라이브러리에 적용하지 못함<br>
<br>
정리<br>
@PostConstruct, @PreDestroy 어노테이션을 주로 사용하되, 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 된다면 @Bean의 intiMethod, destroyMethod를 사용하자.<br>
<br>
<h2>빈 스코프</h2><br>
빈이 존재할 수 있는 범위<br>
<br>
<h2>스프링의 스코프 지원</h2><br>
1. 싱글톤: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프<br>
- 싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환.<br>
- 싱글톤 빈 요청:<br>
 싱글톤 스코프의 빈을 스프링 컨테이너에 요청 <br>
 -> 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환<br>
 -> 이후에 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스피링 빈을 반환<br>
- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행됨.<br>
- 싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 컨테이너가 종료될 때 빈의 종료 메서드가 실행됨.<br>
<br>
2. 프로토타입: 스프링 컨테이너가 프로토타입 빈의 생성과 의존관계 주입까지만 관여하는 매우 짧은 범위의 스코프<br>
- 프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성하여 반환<br>
- 프로토타입 빈 요청:<br>
 프로토타입 스코프의 빈을 스프링 컨테이너에 요청:<br>
 -> 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성하고, 필요한 의존관계를 주입<br>
 -> 생성한 프로토타입 빈을 클라이언트에 반환<br>
 -> 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성하여 반환<br>
- 핵심:<br>
 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리,<br>
 생성된 프로토타입 빈을 관리하지 않음,<br>
 따라서, @PreDestory 같은 종료 메서드가 호출되지 않음<br>
- 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행됨.<br>
- 프로토타입 빈을 2번 조회하면 완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행됨.<br>
- 싱글톤 빈과 함께 사용시 문제점:<br>
 싱글톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야 함.<br>
<br>
3. 웹 관련 스코프<br>
- request: 웹 요청이 들어오고 나갈 때까지 유지되는 스코프<br>
- session: 웹 세션이 생성되고 종료될 때까지 유지되는 스코프<br>
- application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프<br>

<h2>빈 스코프 등록 예시</h2><br>
- 컴포넌트 스캔 자동 등록<br>
@Scope(“prototype”)<br>
@Component<br>
- 수동 등록<br>
@Scope(“prototype”)<br>
@Bean<br>
<br>
<h2>싱글톤에서 프로토타입 빈 사용</h2><br>
싱글톤 빈이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이라, 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지, 사용할 때마다 새로 생성되는 것은 아님.<br>
- 문제점:<br>
 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함계 계속 유지되는 것이 문제<br>
<br>
! 여러 빈에서 같은 프로토타입 빈을 주입 받으면, 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성됨.<br>
<br>
<h2>프로토타입 스코프, 싱글톤 빈과 함께 사용시 Provider로 문제 해결</h2><br>
1. 스프링 컨테이너에 요청<br>
- 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것<br>
- 핵심 코드<br>
@Autowired<br>
private ApplicationContext ac;<br>
public int logic() {<br>
 PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);<br>
 prototypeBean.addCount();<br>
 int count = prototypeBean.getCount();<br>
 return count;<br>
}<br>
- 단점:<br>
 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워짐.<br>
<br>
2. ObjectFactory, ObjectProvider<br>
ObjectProvider는 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공함.<br>
- ObjectProvider의 getObject()를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아 반환함.(DL)<br>
- 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기가 쉬워짐.<br>
- 특징:<br>
 ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존<br>
 ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요없음, 스프링에 의존<br>
<br>
3. JSR-330 Provider<br>
javax.inject.Provider라는 JSR-330 자바 표준을 사용<br>
- 이 방법을 사용하려면 gradle에 라이브러리를 추가해야함<br>
 스프링부트 3.0 미만: javax.inject:javax.inject:<br>
 스프링부터 3.0 이상: jakarta.inject:jakarta.inject-api:2.0.1<br>
- provider의 get()을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환.(DL)<br>
- 특징:<br>
 기능이 단순하므로 단위테스트를 만들거나, mock 코드를 만들기 훨씬 쉬워짐<br>
 get() 메서드 하나로 기능이 매우 단순함<br>
 별도의 라이브러리가 필요<br>
 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능<br>
<br>
! @Lookup 어노테이션을 사용하는 방법도 있음.<br>
<br>
<h2>정리</h2><br>
매번 사용할 때마다 의존관계 주입이 완료된 새로운 객체가 필요하면 프로토타입 빈 사용.<br>
ObjectProvider, JSR330 Provider등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용 가능.<br>
<br>
<h2>웹 스코프</h2><br>
- 특징: <br>
 웹 환경에서만 동작<br>
 프로토타입과 다르게 스프링이 해당 스코프의 종료 시점까지 관리하여 종료 메서드가 호출됨<br>
- request: HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리됨<br>
 동시에 여러 HTTP 요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분하기 힘들 때 사용하기 좋음<br>
<br>
- session: HTTP Session과 동일한 생명주기를 가지는 스코프<br>

- application: 서블릿 컨텍스트와 동일한 생명주기를 가지는 스코프<br>

- websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프<br>

스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, reqeust 스코프 빈은 아직 생성되지 않아 오류가 발생.<br>
1. Provider<br>
- ObjectProvider.getObject()를 호출하는 시점까지 request 스코프 빈의 생성을 지연할 수 있음.<br>
- ObjectProvider.getObject()를 호출하는 시점에는 HTTP 요청이 진행중이므로 request 스코프 빈의 생성이 정상 처리됨.<br>
- Controller, Service에서 각각 한번씩 따로 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환됨.<br>
<br>
2. Proxy<br>
- 핵심: proxyMode=ScopedProxyMode.TARGET_CLASS<br>
 적용 대상이 인터페이스가 아니면 TARGET_CLASS,<br>
 적용 대상이 인터페이스면 INTERFACES<br>
- 가짜 프록시 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있음.<br>
- 동작 원리:<br>
 CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입.<br>
 이 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있음.<br>
 가짜 프록시 객체는 실제 request scope와는 관계가 없음. <br>
 그냥 가짜이고, 내부에 단순한 위임 로직만 있고, 싱글톤처럼 동작.<br>
- 특징:<br>
 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 함.<br>
 어노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있음.<br>
 웹 스코프가 아니어도 프록시 사용 가능<br>
- 주의점:<br>
 싱글톤과 비슷하지만 다르게 동작하기 때문에 주의해서 사용해야함.<br>
 꼭 필요한 곳에만 최소화하여 사용.<br>
 무분별하게 사용하면 유지보수하기 어려워짐.<br>
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
