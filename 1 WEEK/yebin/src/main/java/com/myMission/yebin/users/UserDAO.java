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

    public Map<String,Long> getAllUser(){
        Map<String,Long> userInformationMap = new HashMap<>();
        for(UserVO user : userList){
            userInformationMap.put(user.getUserId(),user.getUserSequence());
        }
        return userInformationMap;
    }
    public void signUp(String userId, String userPassword, String checkUserPassword){
        if(!userPassword.equals(checkUserPassword)){
            return;
        }

        setUser(userId,userPassword);

    }

