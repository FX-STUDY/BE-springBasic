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

지금까지 작성된 java Code 를 Spring 으로 전환하세요.
작성된 코드내에서 변환바랍니다.

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
Spring IoC, DI, 스프링 컨테이너에 대해서 공부하세요.
스프링 컨테이너를 사용하면 어떤 장점에 대해 공부하세요.

[ 🧐 Member : Study AND ] <br>
- main fork 동기화 후 작업 진행
- 개인 folder 없습니다. 이전 Answer file 활용해서 작성 바랍니다.
- ANSWER README 에 작성 하되, 기본 포맷은 기본으로 작성하고, 개별 Custom 후 추가 정보 기입

---

## 주요 이론 요약
### 의존관계 주입 (Dependency Injection, DI)
의존관계를 외부에서 주입해주는 것을 말하며 제어의 역행을 통해 이루어진다. <br>
객체 간의 관계를 동적으로 주입하여 유연성을 확보하고 결합도를 낮출 수 있다.

###
### 제어의 역전 (Inversion of Control, IoC)
제어란 객체를 생성하는 주도권을 의미한다.<br>
프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 말한다.

###
### 스프링 컨테이너
**스프링 컨테이너는 객체의 생명 주기를 관리하며 생성된 객체들에게 추가적인 기능을 제공한다.** <br>

스프링 컨테이너는 XML, 어노테이션 기반의 자바 설정 클래스로 만들 수 있다. <br>

스프링 컨테이너는 BeanFactory와 ApplicationContext 두 종류의 인터페이스로 구현되어 있다. <br>

**객체 간의 의존성을 낮추어 결합도를 낮추고 높은 캡슐화를 위해 사용된다.** <br>

**의존성 주입(DI)을 통해 애플리케이션의 컴포넌트를 관리할 수 있다.** <br>

**스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라고 한다.**

---

## ISSUE
    -  Nothing

---

## Solution
    -  Nothing

---

## About
### @Configuration
설정 파일을 만들거나 Bean을 수동으로 등록하기 위한 어노테이션이다.

###
### @Bean
Bean은 스프링 컨테이너에 의해 관리되는 재사용 가능한 소프트웨어 컴포넌트다. <br>
@Bean 어노테이션을 통해 메서드로부터 반환된 객체를 스프링 컨테이너에 등록한다.

###
### ApplicationContext
스프링 컨테이너라고 한다. <br>
ApplicationContext는 BeanFactory 인터페이스의 하위 인터페이스이다. <br>
즉, ApplicationContext는 BeanFactory에 부가 기능을 추가한 것이다. <br>

부가 기능은 다음과 같다.

1) 메시지 소스를 활용한 국제화 기능(MessageSource)
2) 환경 변수(EnvironmentCapable) : 로컬, 개발, 운영 등을 구분해서 처리
3) 애플리케이션 이벤트(ApplicationEventPublisher) : 이벤트를 발행하고 구독하는 모델을 지원
4) 편리한 리소스 조회(ResourceLoader) : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

스프링 컨테이너를 부를 때 BeanFactory, ApplicationContext를 구분해서 말하지만 BeanFactory를 직접적으로 사용하는 경우는 거의 없다. <br>
왜냐하면 ApplicationContext가 BeanFactory의 모든 기능을 가지고 있기 때문이다.

###
### AnnotationConfigApplicationContext
AnnotationConfigApplicationContext는 ApplicationContext 인터페이스의 구현체이다.

###
### getBean()
Bean 이름으로 Bean 객체를 조회한다.

---

## Question To Reader
    -  Nothing