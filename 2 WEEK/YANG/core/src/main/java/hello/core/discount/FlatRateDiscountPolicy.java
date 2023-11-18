package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FlatRateDiscountPolicy implements DiscountPolicy{
    private int FlatRateDiscount = 10;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return price * (FlatRateDiscount / 100);
        }
        return 0;
    }
}
