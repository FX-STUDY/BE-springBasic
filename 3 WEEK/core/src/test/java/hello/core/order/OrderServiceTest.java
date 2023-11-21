package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    OrderService orderService = ac.getBean("orderService", OrderService.class);

    @Test
    void createOrder() {

        Member member = new Member("memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}