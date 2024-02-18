package SEOB.SEOB.discount;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private final static int discountRateAmount = 30;

    @Override
    public int discount(Member member, int price) {
            if(member.getGrade().equals(GradeType.VIP))
                return price * discountRateAmount / 100;
            return 0;
    }
}
