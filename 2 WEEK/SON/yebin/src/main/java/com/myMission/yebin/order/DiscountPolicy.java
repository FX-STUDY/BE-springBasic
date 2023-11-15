package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;

public class DiscountPolicy {

    public int discountFixed(GradeVO grade){
        if(grade.equals(GradeVO.VIP)){
            return 1000;
        }
        return 0;
    }
    // 회원 등급에 따른 할인률 세팅
    public int discountRate(GradeVO grade){
        // VIP 는 10% 할인
        if (grade.equals(GradeVO.VIP)) {
            return 10;
        }
        // 그외는 할인 X
        return 0;
    }

    // 회원 등급에 따른 상품 가격 계산
    public Integer calculateItemPrice(Integer itemPrice, GradeVO grade, DiscountHow how){
        Integer calculatedItemPrice;
        // 고정 금액 할인
        calculatedItemPrice = itemPrice - discountFixed(grade);

        // 정률 할인
        if (how.equals(DiscountHow.RATE)){
            calculatedItemPrice = itemPrice - (itemPrice*discountRate(grade)/100);
        }
        return calculatedItemPrice;
    }


}
