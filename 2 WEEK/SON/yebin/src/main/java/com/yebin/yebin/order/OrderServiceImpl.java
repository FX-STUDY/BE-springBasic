package com.yebin.yebin.order;

import com.yebin.yebin.dependency.DependencyInjection;
import com.yebin.yebin.discount.DiscountPolicy;
import com.yebin.yebin.discount.FixDiscountPolicy;
import com.yebin.yebin.discount.RateDiscountPolicy;
import com.yebin.yebin.member.Member;
import com.yebin.yebin.member.MemberRepository;
import com.yebin.yebin.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final DependencyInjection dependencyInjection = new DependencyInjection();
    private final MemberRepository memberRepository = dependencyInjection.memberRepository();
    private final DiscountPolicy discountPolicy = dependencyInjection.discountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        float discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    @Override
    public float calculatePrice(int itemPrice, float discountPrice) {
        return itemPrice - discountPrice;
    }
}
