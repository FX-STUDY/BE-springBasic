package com.myMission.week2.users;

public class UserDAO {

    public void signUp(String userId, String userPassword, String checkUserPassword){
        UserVO userVO = new UserVO();
        userVO.setUserId(userId);

        if(!userPassword.equals(checkUserPassword)){
            return;
        }
        userVO.setUserPassword(userPassword);
    }
}
