package com.yebin.yebin.order;

import com.yebin.yebin.discount.DiscountPolicy;
import com.yebin.yebin.discount.FixDiscountPolicy;
import com.yebin.yebin.discount.RateDiscountPolicy;
import com.yebin.yebin.member.Member;
import com.yebin.yebin.member.MemberRepository;
import com.yebin.yebin.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

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
