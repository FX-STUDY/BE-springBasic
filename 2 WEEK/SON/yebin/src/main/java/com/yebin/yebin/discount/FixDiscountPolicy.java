package com.yebin.yebin.discount;

import com.yebin.yebin.member.Grade;
import com.yebin.yebin.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public float discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else {
            return 0;
        }

    }
}
