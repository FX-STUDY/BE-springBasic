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
## 요구사항
[ 새로운 할인 정책 개발 ]<br><br>

기획자 : <br>
Service Open 이 일주일 남았지만 `고정 금액 할인` -> `정률 (%) 할인` 으로 변경하고 싶다.<br>
기존엔 VIP 에게 1000원을 할인해 드렸지만, 10% 할인 정책으로 변경해 주세요.<br>

개발자 : <br>
일주일 남았는데.... <br>

기획자 : <br>
Agile 선언 모르나요? " 계획을 따르기보다는 변화에 대응하라 " <br>
https://agilemanifesto.org/iso/ko/manifesto.html <br>

개발자 :<br>
...<br>

## Study 방법
[ 😎 Leader's 요구사항 ] <br>
이전 코드에 `OOP 설계 원칙` 을 위반한 사례를 찾아 README 에 Update 해주세요.
또 발견된 위반 사례를 `OOP 설계 원칙` 을 잘 지켜 수정해 주세요.

[ 🧐 Member : Study AND ] <br>
   - main fork 동기화 후 작업 진행
   - 개인 folder 내 에서 작업 할 것
   - ANSWER README 에 작성 하되, 기본 포맷은 기본으로 작성하고, 개별 Custom 후 추가 정보 기입

---

## 주요 이론 요약
### 객체지향 설계 5대 원칙 (SOLID)
* **단일 책임 원칙 (Single Responsibility Principle, SRP)** : 한 클래스는 하나의 책임만 가져야 한다.
* **개방-폐쇄 원칙 (Open-Closed Principle, OCP)** : 변경에는 닫혀 있어야 하고, 확장에는 열려 있어야 한다.
* **리스코프 교체 원칙 (Liskov Substitution Principle, LSP)** : 상위 클래스의 객체는 언제나 자신의 하위 클래스의 객체로 교체할 수 있어야 한다.
* **인터페이스 분리 원칙 (Interface Segregation Principle, ISP)** : 클라이언트는 자신이 사용하지 않는 메서드와 의존 관계를 맺으면 안 된다.
* **의존 관계 역전 원칙 (Dependency Inversion Principle, DIP)** : 클라이언트는 구체 클래스가 아닌 추상 클래스(인터페이스)에 의존해야 한다.

###
### 의존관계 주입 (Dependency Injection, DI)
의존관계를 외부에서 주입해주는 것을 말하며 제어의 역행을 통해 이루어진다. <br>
객체 간의 관계를 동적으로 주입하여 유연성을 확보하고 결합도를 낮출 수 있다.

###
### 제어의 역전 (Inversion of Control, IoC)
제어란 객체를 생성하는 주도권을 의미한다.<br>
프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 말한다.

---

## ISSUE
### 위반 사례
* **DIP**
    1. MemberServiceImpl Class는 MemoryMemberRepository 구현 클래스를 의존한다.
    2. OrderServiceImpl Class는 MemoryMemberRepository, FixDiscountPolicy 구현 클래스를 의존한다.


* **OCP**
    1. 할인 정책이 바뀔 경우 OrderServiceImpl 소스코드를 변경해야 한다.

---

## Solution
### MemberServiceImpl 멤버 변수 초기화 삭제 및 생성자 생성
```java
private final MemberRepository memberRepository;

public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}
```
###
### OrderServiceImpl 멤버 변수 초기화 및 생성자 생성
```java
private final MemberRepository memberRepository;
private final DiscountPolicy discountPolicy;

public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
}
```
###
### 의존관계 주입 (Dependency Injection, DI)
```java
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
```

---

## About
### Clean Code 위반

```java
if (member.getGrade() == Grade.VIP) {
    return price * (FlatRateDiscount / 100);
} else {
    return 0;    
}
```
### 해결

```java
if (member.getGrade() == Grade.VIP)
    return price * (FlatRateDiscount / 100);
    
return 0;
```

###
### 멤버 변수 직관성 저하
```java
private double FlatRateDiscount = 0.1;
```
### 해결
```java
private int FlatRateDiscount = 10;
```

---

## Question To Reader
    -  Nothing