package com.myMission.yebin.users;

// 회원정보 관련

public class UserVO {
    // 회원 아이디
    private String userId;
    // 회원 패스워드
    private String userPassword;
    // 회원 번호
    private Long userSequence;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getUserSequence() {
        return userSequence;
    }

    public void setUserSequence(Long userSequence) {
        this.userSequence = userSequence;
    }
}
