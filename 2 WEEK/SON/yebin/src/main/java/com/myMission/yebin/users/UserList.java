package com.myMission.yebin.users;

import java.util.ArrayList;
import java.util.List;

public class UserList{
    private static List<UserVO> userList;

    public static List<UserVO> getInstance(){
        if(userList == null){
            userList = new ArrayList<>();
        }
        return userList;
    }
}
