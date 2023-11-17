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

1. DI : 하나의 객체가 다른 객체의 의존성을 제공하는 테크닉
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

## Solution

1. OrderServiceImpl에서 생성자를 통해 의존관계를 주입 받도록 코드를 수정한다.

        private final DiscountPolicy discountedPolicy;

        public OrderServiceImpl(DiscountPolicy discountedPolicy) {
            this.discountedPolicy = discountedPolicy;
        }


## About

Please enter your personal feelings, what you learned, and what you need to learn here.

## Question To Reader

After completing the mission, please enter any suggestions or questions.

