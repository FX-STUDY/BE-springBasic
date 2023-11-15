package SEOB.SEOB.discount;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private int discountRateAmount = 30;

    @Override
    public int discount(Member member, int price) {
            if(member.getGrade() == GradeType.VIP) {
                return discountRateAmount;
            } else {
                return 0;
            }
    }
}
