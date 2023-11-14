package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;

public class DiscountPolicy {
    // 회원 등급에 따른 할인률 세팅
    public int setDiscountByGrade(GradeVO grade){
        // VIP 는 10% 할인
        if (grade.equals(GradeVO.VIP)) {
            return 10;
        }
        // 그외는 할인 X
        return 0;
    }

    // 회원 등급에 따른 상품 가격 계산
    public Integer calculateItemPrice(Integer itemPrice, GradeVO grade){
        return itemPrice - itemPrice * (setDiscountByGrade(grade)/100);
    }


}
