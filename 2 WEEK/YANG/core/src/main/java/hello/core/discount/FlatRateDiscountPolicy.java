package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FlatRateDiscountPolicy implements DiscountPolicy{

    private double flatRateDiscount = 0.1;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return (int) (price * flatRateDiscount);
        }else {
            return 0;
        }
    }
}
