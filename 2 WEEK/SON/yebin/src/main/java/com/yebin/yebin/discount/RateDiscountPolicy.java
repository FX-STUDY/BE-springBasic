package com.yebin.yebin.discount;

import com.yebin.yebin.member.Grade;
import com.yebin.yebin.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private float discountRate = 10;
    @Override
    public float discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * (discountRate/100);
        }

        return 0;
    }
}
