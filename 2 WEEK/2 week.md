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
[ 새로운 할인 정책 개발 ]<br><br>
1. DiscountPolicy 변경 
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

2. 관심사 분리
   - 구현 객체를 생성하고, 연결하는 책임을 별도의 Class 로 구성하시오.

3. 좋은 OOP 설계 5가지 원칙에 대해 공부 README 에 작성바랍니다.

# 2. 객체지향 원리 적용

## 요구사항

[ 새로운 할인 정책 개발 ]

1. DiscountPolicy 변경 기획자 :Service Open 이 일주일 남았지만 `고정 금액 할인` -> `정률 (%) 할인` 으로 변경하고 싶다.기존엔 VIP 에게 1000원을 할인해 드렸지만, 10% 할인 정책으로 변경해 주세요.

개발자 :

일주일 남았는데....

기획자 :

Agile 선언 모르나요? " 계획을 따르기보다는 변화에 대응하라 "

https://agilemanifesto.org/iso/ko/manifesto.html

개발자 :

...

1. 관심사 분리
    - 구현 객체를 생성하고, 연결하는 책임을 별도의 Class 로 구성하시오.
2. 좋은 OOP 설계 5가지 원칙에 대해 공부 README 에 작성바랍니다.

   
## Answer

### RateDiscountPolicy

![스크린샷 2023-11-19 21 04 40](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/e216a10a-088c-44ee-8c54-e31ef7bd35b1)

> 인터페이스 도입 시 장점에 대해 공부 필요


### RateDiscountPolicy

```java
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }

        return 0;

    }
}
```

### Test

```java
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인")
    void vip_o(){

        //given
        Member member = new Member("memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용 안됨")
    void vip_x(){

        //given
        Member member = new Member("memberBasic",Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(0);

    }

}
```

## 새로운 할인 정책 적용과 문제점

할인 정책을 변경하려면 클라이언트인 `OrderServiceImpl` 코드를 고쳐야 한다.

```java
public class OrderServiceImpl implements OrderService{

	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
}
```

역할, 구현 분리 → OK

다형성 활용, 인터페이스와 구현 객체 분리 → OK

OCP, DIP 위반

DIP : 추상 [ 인터페이스 ] 뿐만 아니라 구체 [ 구현 ] 클래스에도 의존하고 있다.

OCP : 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다.

## 해결 방법?

---

DIP 를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다.

![스크린샷 2023-11-19 21 20 21](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/92e9b97e-9321-4890-966e-b834b370d81e)

### 인터페이스에만 의존하도록 코드 변경

```java
public class OrderServiceImpl implements OrderService {
      //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
      private DiscountPolicy discountPolicy;
}
```

실행을 하면 NPE [ null pointer exception ]  가 발생한다.

### 해결 방안

외부에서 클라이언트인 `OrderServiceImpl` 에 `DiscountPolicy` 의 구현 객체를 대신 생성하고 주입해주어야 한다.

## 관심사 분리

애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성**하고, 

**연결**하는 책임을 가지는별도의 설정 클래스 만들기

### AppConfig

```java

package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

}
```

AppConfig 는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.

- `MemberServiceImpl`
- `MemoryMemberRepository`
- `OrderServiceImpl`
- `FixDiscountPolicy`

AppConfig 는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.

- `MemberServiceImpl` → `MemoryMemberRepository`
- `OrderServiceImpl` → `MemoryMemberRepository` , `FixDiscountPolicy`

### MemberServiceImpl - 생성자 주입

```java
package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

설계 변경으로 `MemberServiceImpl` 은 `MemoryMemberRepository` 를 의존하지 않는다.

단지 `MemberRepository` 인터페이스만 의존한다.

`MemberServiceImpl` 입장에서 생성자를 통해 어떤 구현 객체가 들어올지 알 수 없다.

`MemberServiceImpl` 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 ( `AppConfig` ) 에서 결정 됨

`MemberServiceImpl` 은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

![스크린샷 2023-11-19 21 43 24](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/74a2ea7a-d6b6-4025-a590-f7cad86a7edd)

객체의 생성과 연결은 `AppConfig` 가 담당한다. = DIP 완성

### 회원 객체 인스턴스 다이어그램

![스크린샷 2023-11-19 21 44 50](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/fe293950-b46f-4d04-be03-4ad8f09653b0)

`appConfig` 객체는 `memoryMemberRepository` 객체를 생성하고 그 참조값을 `memberServiceImpl` 을 생성하면서 생성자로 전달 된다.

클라이언트인 `memberServiceImple` 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI [ Dependency Injection ] , 의존성 주입이라 한다.

### OrderServiceImpl - 생성자 주입

```java
package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```

## AppConfig 실행

### MemberApp

```java
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member("memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }

}
```

### OrderApp

```java
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member = new Member("memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",10000);

        System.out.println("order = " + order);

    }
}
```

### Test

```java
package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member("memberA",Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);

    }
}
```

```java
package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {

        Member member = new Member("memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
```

`@BeforeEach` 는 각 테스트를 실행하기 전에 호출된다.

![스크린샷 2023-11-19 21 54 43](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/a8b736d4-3abd-4cbb-b874-5bee95292fbb)


## 새로운 구조와 할인 정책 적용

---

FixDiscountPolicy → RateDiscountPolicy 변경

**AppConfig의 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성 (Configuration)하는 영역으로 분리되었다.**

### 사용, 구성의 분리

![스크린샷 2023-11-19 21 56 08](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/8ec39aa6-074d-45fb-8392-7ad031812105)


### 할인 정책의 변경

![스크린샷 2023-11-19 21 56 38](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/de39e7d3-9ba1-4268-bc46-3bbf5fcc1b0b)


`FixDiscountPolicy` → `RateDiscountPolicy` 로 변경해도 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않는다.

### 할인 정책 변경 구성 코드

```java
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
```

이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 `AppConfig` 만 변경하면 된다.
클라이언트 코드인 `OrderServiceImpl` 를 포함해서 **사용 영역**의 어떤 코드도 변경할 필요가 없다.

**구성 영역**은 당연히 변경된다.
