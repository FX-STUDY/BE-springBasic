package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.stereotype.Component;

//DiscountPolicy 구현하여 정률 할인정책 구현
@Component("rateDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountRate = 10;
    @Override
    public int discount(long memberID, int price) {

        MemberService memberService = new MemberServiceImpl();

        Member member = memberService.findMember(memberID);


        if(member.getGrade() == Grade.VIP) {
            int discountAmount = (int)(discountRate * 0.01 * price);
            return discountAmount;
        }
        return 0;
    }
}
