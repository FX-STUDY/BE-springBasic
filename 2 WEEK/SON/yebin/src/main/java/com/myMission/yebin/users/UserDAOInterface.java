package com.myMission.yebin.users;

import com.myMission.yebin.grade.GradeVO;

import java.util.Map;

public interface UserDAOInterface {
    public abstract void saveUser(String userID, GradeVO grade);
    public abstract Map<String,Long> findAllUser();
    public abstract GradeVO findGradeByUserId(String userId);
}
