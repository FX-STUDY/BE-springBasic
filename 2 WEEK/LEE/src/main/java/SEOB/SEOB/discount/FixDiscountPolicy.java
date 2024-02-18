package SEOB.SEOB.discount;

import SEOB.SEOB.domain.GradeType;
import SEOB.SEOB.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private final static int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {

        if(member.getGrade().equals(GradeType.VIP)) //VIP인 경우
            return discountFixAmount;
        return 0;


    }
}
