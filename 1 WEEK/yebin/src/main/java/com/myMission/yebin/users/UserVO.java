package com.myMission.yebin.users;

// 회원정보 관련

import com.myMission.yebin.grade.GradeVO;
import lombok.Getter;

public class UserVO {
    // 회원 아이디
    private String userId;
    // 회원 번호
    private final Long userNumber;
    // 회원 등급
    private String userGrade;

    // Build Pattern ??
    public UserVO(String userId, Long userNumber, GradeVO userGrade){
        this.userId = userId;
        this.userNumber = userNumber;
        this.userGrade = userGrade.toString();
    }

    public String getUserId() {
        return userId;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public String getUserGrade() {
        return userGrade;
    }
}
