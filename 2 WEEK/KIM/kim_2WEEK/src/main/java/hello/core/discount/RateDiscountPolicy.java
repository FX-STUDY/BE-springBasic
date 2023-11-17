package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//DiscountPolicy 구현하여 정률 할인정책 구현
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountRate = 10;
    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return (int)(price - (price * (discountRate / 100)));
        }
        return price;
    }
}
