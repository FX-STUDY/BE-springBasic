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
[ 회원 ] <br>
회원을 가입하고 조회할 수 있다. <br>
회원등급 일반 / VIP 등급이 있다. <br>
회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정) <br>
회원 ID (자동 생성), 회원 이름, 등급
[ 주문과 할인 정책 ] <br>
회원은 상품을 주문할 수 있다. <br>
회원 등급에 따라 할인 정책을 적용할 수 있다. <br>
할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인 적용 (나중에 변경 될 수 있다.) <br>
할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정) <br>
주문 번호 (자동 생성), 상품 이름, 상품 가격, 할인 받은 가격

## ANSWER

# 비즈니스 요구사항

---

### 회원

- 회원을 가입하고 조회할 수 있다.
- 회원은 일반과 VIP 두 가지 등급
- 회원 데이터는 자체 DB를 구출할 수 있고, 외부 시스템과 연동할 수 있다. [ 미확정 ]

### 주문과 할일 정책

- 회원은 상품을 주문할 수 있다.
- 회원 등급에 따라 할인 정책을 적용할 수 있다.
- 할인 정책은 모든 VIP는 1000원을 할인 - 고정 금액 할인을 적용 [ 나중에 변경 될 수 있다. ]
- 할인 정책은 변경 가능성이 높다.

## Member Domain

![스크린샷 2023-11-11 11 27 21](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/893fb79a-4b90-486b-b534-da5b223f0cff)

## Member Class Diagram

![스크린샷 2023-11-11 11 28 30](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/8a388b8e-edda-4ecb-9545-7feaf3252745)

## 회원 객체 다이어그램

![스크린샷 2023-11-11 11 28 36](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/83699dbd-be8f-48ff-aa25-c82ec4ee0652)

## 회원 도메인 개발

---

### Grade

```java
package hello.core.member;

public enum Grade {

    BASIC,
    VIP

}
```

상수, enum 등은 변수명을 대문자로 사용하는 관례가 있다.

### Member Entity

```java
package hello.core.member;

public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Member( String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
```

### Member Repository Interface

```java
package hello.core.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
```

### Member Repository 구현체

```java
package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long SEQUENCE = 0L;

    @Override
    public void save(Member member) {
        member.setId(++SEQUENCE);
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
```

> 참고 : `HashMap` 은 동시성 ISSUE 가 발생할 수 있다. 이런 경우 `ConcurrentHashMap` 을 사용하자.
> 

### Member Service Interface

```java
package hello.core.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
```

### Member Service 구현체

```java
package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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

### 회원 도메인 실행과 테스트

회원 가입 main

```java
package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member = new Member("memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }

}
```

Application logic 으로 이렇게 Test 하는 것은 좋은 방법이 아니다. JUnit Test 를 사용하자

### 회원 가입 테스트

```java
package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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

# 주문과 할인 도메인 설계

---

### 주문 도메인 협력, 역할, 책임

![스크린샷 2023-11-11 11 35 34](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/c107fbf7-d029-4336-bb47-1082079b8fd2)

1. 주문 생성 : 클라이언트는 주문 서비스에 주문 생성을 요청한다.
2. 회원 조회 : 할인을 위해서 회원 등급이 필요하다. 그래서 주문 서비스는 회원 저장소에서 회원을 조회한다.
3. 할인 적용 : 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임한다.
4. 주문 결과 반환 : 주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다.

### 주문 도메인 전체

![스크린샷 2023-11-11 11 37 21](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/f25b7df5-8930-41a9-ad03-5abf94aeb244)


역할과 구현을 분리해서 자유롭게 구현 객체를 조립할 수 있게 설계한다.

### 주문 도메인 클래스 다이어그램

![스크린샷 2023-11-11 11 38 14](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/b52aee65-a5d0-4a11-8dae-1b25a0b1600f)


### 주문 도메인 객체 다이어그램1

![스크린샷 2023-11-11 11 38 47](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/df91550c-b8b5-47ac-a35b-ec2b468a61a4)

### 주문 도메인 객체 다이어그램2

![스크린샷 2023-11-11 11 38 53](https://github.com/FX-STUDY/BE-springBasic/assets/100909578/3c353294-4ba5-4f01-9752-f873f64f3d66)

## 주문과 할인 도메인 개발

---

### 할인 정책 Interface

```java
package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member , int price);

}
```

### 정액 할인 정책 구현체

```java
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else {
            return 0;
        }

    }
}
```

### 주문 엔티티

```java
package hello.core.order;

public class Order {

    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice){
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice(){
        return itemPrice - discountPrice;
    }

    public int getDiscountPrice(){
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
```

### 주문 서비스 인터페이스

```java
package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
```

### 주문 서비스 구현체

```java
package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```

### 주문과 할인 정책 테스트

```java
package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {

        Member member = new Member("memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
```
## HOME WORK
1. 위 코드의 핵심은 무엇일까요?
2. 위 코드의 문제점은 무엇일까요?
3. 그 해결 방법은 무엇일까요?
