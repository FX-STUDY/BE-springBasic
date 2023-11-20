package com.yebin.yebin.dependency;

import com.yebin.yebin.discount.DiscountPolicy;
import com.yebin.yebin.discount.RateDiscountPolicy;
import com.yebin.yebin.member.MemberRepository;
import com.yebin.yebin.member.MemberService;
import com.yebin.yebin.member.MemberServiceImpl;
import com.yebin.yebin.member.MemoryMemberRepository;
import com.yebin.yebin.order.OrderService;
import com.yebin.yebin.order.OrderServiceImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public DiscountPolicy discountPolicy(){
        // 할인 정책이 바뀌면 이 부분만 바꿔주면 된다.
        return new RateDiscountPolicy();
    }

    public MemberRepository memberRepository(){
        // DB 연결하면 이 부분만 바꿔주면 된다.
        return new MemoryMemberRepository();
    }

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
}
