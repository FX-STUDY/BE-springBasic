package SEOB.SEOB.discount;

import SEOB.SEOB.domain.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
