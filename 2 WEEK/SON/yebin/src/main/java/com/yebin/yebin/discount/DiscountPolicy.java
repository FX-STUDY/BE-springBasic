package com.yebin.yebin.discount;

import com.yebin.yebin.member.Member;

public interface DiscountPolicy {
    float discount(Member member, int price);
}
