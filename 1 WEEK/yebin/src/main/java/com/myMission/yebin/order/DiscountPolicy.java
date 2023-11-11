package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;

public class DiscountPolicy {
    public int setDiscountByGrade(Enum grade){
        int discountRate = 0;

        if (grade.equals(GradeVO.VIP)) {
            discountRate = 1000;
        }

        return discountRate;
    }


}
