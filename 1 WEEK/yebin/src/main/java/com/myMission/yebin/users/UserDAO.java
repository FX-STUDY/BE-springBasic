package com.myMission.yebin.users;

import org.apache.catalina.User;

import java.util.List;

public class UserDAO {
    private UserVO userVO = new UserVO();
    public UserVO setUser(String userId, String userPassword){
        userVO.setUserId(userId);
        userVO.setUserPassword(userPassword);
        return userVO;
    }

    public String getUser(){
        String userId = userVO.getUserId();
        Long userSequence = userVO.getUserSequence();
        return userId+", "+userSequence;
    }
    public void signUp(String userId, String userPassword, String checkUserPassword){
        if(!userPassword.equals(checkUserPassword)){
            return;
        }

        userVO = setUser(userId,userPassword);

        Long userSequence = userVO.getUserSequence();
        userVO.setUserPassword(userPassword);
        userVO.setUserSequence(1L);
    }

