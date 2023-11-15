package com.myMission.yebin.users;

import com.myMission.yebin.grade.GradeVO;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements UserDAOInterface{
    private List<UserVO> userList = UserList.getInstance();
    private static Long userNumber = 1L;
    @Override
    public void saveUser(String userId, GradeVO grade){
        UserVO userVO = new UserVO(userId, userNumber++, grade);

        userList.add(userVO);
    }

    @Override
    public Map<String, Long> findAllUser(){
        Map<String,Long> userInformationMap = new HashMap<>();

        for(UserVO user : userList){
            userInformationMap.put(user.getUserId(),user.getUserNumber());
        }
        return userInformationMap;
    }

    @Override
    public GradeVO findGradeByUserId(String userId){
        Map<String, GradeVO> userGradeMap = new HashMap<>();
        for(UserVO user : userList){
            userGradeMap.put(user.getUserId(),user.getUserGrade());
        }
        return userGradeMap.get(userId);
    }

}
