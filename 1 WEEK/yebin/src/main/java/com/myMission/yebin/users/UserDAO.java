package com.myMission.yebin.users;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    //private UserVO userVO = new UserVO();
    private ArrayList<UserVO> userList = new ArrayList<>();
    private static Long userSequence = 1L;
    public void setUser(String userId, String userPassword){
        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        userVO.setUserPassword(userPassword);
        userVO.setUserSequence(userSequence++);

        userList.add(userVO);
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

