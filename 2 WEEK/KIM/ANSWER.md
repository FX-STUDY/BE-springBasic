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
   개방 폐쇄 원칙에 따라 수정을 지양하여 FixDiscountPolicy의 수정 대신 DiscountPolicy를 구현한 RateDiscountPolicy를 구현하여 해결하였다. 또한 의존 역전 원칙을 준수하여 인터페이스를 구현하여 생성하였고, 가격의 정률 할인이라는 하나의 기능만을 구현하여 단일 책임 원칙을 준수하였다.


## ISSUE

Please enter your issue details here.

## Solution

Please describe your solution in detail here.

## About
   solid 원칙 -> single responsibility principle, open-closed principle, liskov substitution principle, interface segregation principle, dependency inversion principle 5가지 원칙의 앞 이니셜을 따서 solid원칙이라 부른다.

   - single responsibility principle ->
      단일 책임 원칙, 하나의 요소가 하나의 책임만을 가져야 한다 -> 코드의 가독성을 높이고, 버그를 예방한다. 

   - open-closed principle ->
      개방 폐쇄 원칙, 확장에는 열려있고 수정에는 닫혀 있다는 개념 -> 추상화와 다형성을 활용하여 클래스 설계한다.

   - liskov substitution principle ->
      리스코프 치환 원칙, 자식 클래스가 항상 부모 클래스를 대신 할 수 있다는 개념 ->
         ex) 도형 클래스(부모)                               사각형 클래스(자식)
            1) 도형은 넓이를 가진다            ===>         1) 사각형은 넓이를 가진다
            2) 도형은 둘레를 가진다            ===>         2) 사각형은 둘레를 가진다
            3) 도형은 각을 가진다                           3) 사각형은 각을 가진다

            도형 클래스(부모)                                 원 클래스(자식)
            1) 도형은 넓이를 가진다            ===>         1) 원은 넓이를 가진다
            2) 도형은 둘레를 가진다            ===>         2) 원은 둘레를 가진다
            3) 도형은 각을 가진다                           3) 원은 각을 가진다
            
            이때 원 클래스가 부모의 3번 메소드를 수행하지 못하므로 해당 클래스는 자식 클래스가 부모 클래스를 대체하지 못하여 리스코프 치환원칙을 준수하지 못하고 있다고 할 수 있다. 따라서 3번의 메소드를 삭제, 또는 수정하여 리스코프 치환원칙을 준수 하게끔 할 수 있다.

   - interface segregation principle ->
      인터페이스 분리 법칙, 클래스 내에 사용하지 않는 인터페이스는 구현하지 않는다, 즉 자신이 사용하지 않는 메소드에 의존하지 않는다 -> 불필요한 의존도성을 줄이고 코드의 결합도를 낮출 수 있다. 

   - dependency inversion principle ->
      의존 역전의 원칙, 의존관계에 있어서 수정 가능성이 높은 것보다 낮은 것에 의존해야 한다. 객체지향 관점에서 볼 때 수정 가능성이 높은 것은 주로 구체화된 클래스들을 뜻하고, 수정 가능성이 낮은 것들은 추상클래스나 인터페이스를 뜻한다. -> 코드의 재사용성과 유연성을 향상시킬 수 있다.

## Question To Reader

After completing the mission, please enter any suggestions or questions.

