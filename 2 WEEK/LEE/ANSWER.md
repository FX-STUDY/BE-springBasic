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

   ### SOLID 객체지향 설계 5가지 원칙
   - SRP (Single Responsibility Principle) 단일 책임 원칙
      - 하나의 클래스는 하나의 책임만 가져야한다
      - 클래스를 변경하지 이유는 단 하나여야 한다. 변경이 있을 때 파급 효과가 적어야 한다.
         - 이를 지키지 않으면, 한 책임의 변경에 의해 다른 책임과 관련된 코드에 영향을 미칠 수 있다. 결국, 유지보수가 매우 비효율적이게 된다.
<br><br><br>
        
   - OCP (Open-Closed Principle) 개방-폐쇄 원칙
      - 소프트웨어 요소는 확정에는 열려 있으나 변경에는 닫혀 있어야 한다.
      - 즉, 기존의 코드를 변경하지 않고 기능을 수정, 추가할 수 있도록 설계해야한다.
      - 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현
     
어떤 모듈의 기능을 수정할 때, 해당 모듈을 이용하는 모든 모듈 또한 수정한다면 유지보수가 복잡해짐.
따라서 OCP를 적용해 기존 코드를 변경하지 않아도 기능을 수정, 추가할 수 있게 해야함
<br><br><br>

   - LSP (Liskov Substitution Principle) 리스코프 치환 원칙
      - 하위 타입 객체는 상위 타입 객체에서 가능한 행위를 수행할 수 있어야 한다.
         - 즉, 상위 타입 객체를 하위 타입 객체로 대체하여도 정상적으로 동작해야 한다.
      - 다형성에서 하위 클래스는 인터페이스의 규약을 다 지켜야 한다.
      - 상속 관계에서는 꼭 일반화 관계(IS-A)가 성립해야 한다.
      - 상속 관게가 아닌 클래스들을 상속관계로 설정하면, LSP 위반이다.
<br><br><br>

   - ISP (Interface Segregation Principle) 인터페이스 분리 원칙
      - 클라이언트는 자신이 사용하는 메소드에만 의존해야 한다.
      - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 한 개보다 낫다.
      - 인터페이스는 해당 인터페이스를 사용하는 클라이언트를 기준으로 잘게 분리되어야 한다.
<br><br><br>

   - DIP (Dependency Inversion Principle) 의존 역전 원칙
      프로
      - 의존 관계를 맺을 때, 변하기 쉬운 구체적인 것 보다는 변하기 어려운 추상적인 것에 의존해야 한다는 것이다.
         - 즉, 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻이다.
         - 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다. 구현체에 의존한다면 변경에 어려움이 생긴다
         - 고수준 모듈은 저수준 모듈의 구현에 의존해서는 안된다.
            - 저수준 모듈이 변경되어도 고수준 모듈은 변경이 필요없는 형태가 이상적이다.
<br><br><br>
   




### DI : 하나의 객체가 다른 객체의 의존성을 제공하는 테크닉
   - 의존성 주입에는 3가지 방법 존재. 
   1. 생성자 주입 (Constructor Injection) 
      - Spring에서 권장되는 의존 관계 주입 방식
      - 생성자 주입만이 final 키워드를 사용할 수 있음
      - 객체의 불변성이 보장
   2. Setter 주입 (Setter Injection) 
      - final 키워드를 사용할 수 없어 불변성이 보장되지 않음 -> 객체가 변할 가능성이 존재
      - JUnit 테스트가 어려워짐
      - 단일책임원칙(SRP) 위반
   3. 필드주입 (Field Injection)
      - 역시 final 키워드 사용 불가
      
   - Spring 개발에서 생성자 주입을 사용하기!!

## ISSUE

1. RateDiscountPolicy 클래스를 구현했지만 실제 적용하기 위해서는 OrderServiceImpl에서 수정작업을 해주어야 한다.
    - OCP 위반
    - FixDiscountPolicy(구현 클래스) 에 의존중임 -> DIP 위반

2. calculatePrice 구현하였지만 Order Class의 메소드를 수정하여 NORMAL에는 제대로 된 값이 나오지 않는 문제

## Solution

1. OrderServiceImpl에서 생성자를 통해 의존관계를 주입 받도록 코드를 수정한다.

        private final DiscountPolicy discountedPolicy;

        public OrderServiceImpl(DiscountPolicy discountedPolicy) {
            this.discountedPolicy = discountedPolicy;
        }
2. solution......

## About

Please enter your personal feelings, what you learned, and what you need to learn here.

## Question To Reader

After completing the mission, please enter any suggestions or questions.

