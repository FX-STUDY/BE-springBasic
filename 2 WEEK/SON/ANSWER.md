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
Object Oriented Programming 설계 원칙 <br>
<hr>
OOP의 5원칙 ( SOLID ) <br>
SRP ( Single Responsibility Prindiple ) : 한 클래스는 하나의 책임만 가져야 한다. <br>
OCP ( Open-Closed Prindiple ) : 확장에는 열려있으나, 변경에는 닫혀있어야한다. <br>
LSP ( Liskov's Substitution Principle ) : 하위객체는 상위객체와 교체할 수 있어야한다. <br>
ISP ( Interface Segregation Principle ) : 관심사에 맞게 인터페이스를 분리해야한다. <br>
DIP ( Dependency Inversion Principle ) : 구체화에 의존하지말고 추상화에 의존해야한다. <br>
<hr>
OOP의 4가지 특징 <br>
1. 캡슐화 2. 상속 3. 추상화 4. 다형성

## ISSUE

SRP 위반 --- <br>
 Order 클래스 - calculatedPrice() 메서드가 Order 클래스내에 있으면 Order 클래스가 여러개의 책임을 갖으므로 SRP위반이라 생각됩니다.<br>
OCP 위반 --- <br>
  <br>
LSP 위반 --- <br>
 <br>
ISP 위반 --- <br>
 <br>
DIP 위반 --- <br>
 <br>
## Solution

OCP 위반 --- <br>
Order 클래스 - calculatedPrice()를 Order 클래스에서 빼내어 OrderService 클래스로 옮겨 책임을 한가지만 갖도록 해줍니다.

## About

Please enter your personal feelings, what you learned, and what you need to learn here.

## Question To Reader

After completing the mission, please enter any suggestions or questions.

