package com.yebin.yebin.dependency;

import com.yebin.yebin.discount.DiscountPolicy;
import com.yebin.yebin.discount.RateDiscountPolicy;
import com.yebin.yebin.member.MemberRepository;
import com.yebin.yebin.member.MemoryMemberRepository;

public class DependencyInjection {
    public DiscountPolicy discountPolicy(){
        // 할인 정책이 바뀌면 이 부분만 바꿔주면 된다.
        return new RateDiscountPolicy();
    }

    public MemberRepository memberRepository(){
        // DB 연결하면 이 부분만 바꿔주면 된다.
        return new MemoryMemberRepository();
    }
}
