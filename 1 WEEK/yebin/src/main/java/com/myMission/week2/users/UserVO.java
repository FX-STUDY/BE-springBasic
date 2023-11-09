package com.myMission.week2.users;

import lombok.Getter;
import lombok.Setter;

// 회원정보 관련
@Getter
@Setter
public class UserVO {
    // 회원 아이디
    private String userId;
    // 회원 패스워드
    private String userPassword;
    // 회원 번호
    private int userSequence;
}
