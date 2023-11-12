package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;

public class DiscountPolicy {
    public int setDiscountByGrade(GradeVO grade){
        // VIP 는 1000원 할인
        if (grade.equals(GradeVO.VIP)) {
            return 1000;
        }

        // 그외는 할인 X
        return 0;
    }


}
