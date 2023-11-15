package com.example.kim_2WEEK.user;

import com.example.kim_2WEEK.item.ItemVO;

import java.util.*;

public class UserDAO {
    Scanner sc = new Scanner(System.in);
    private static Map<UserVO,List<ItemVO>> userList = new HashMap<>();
    private static int sequence = 0;
    public void signUp() {

        while(true) {
            System.out.println("이름을 입력하세요: ");
            String name = sc.next();
            if(name != null) {
                UserVO userVO = new UserVO();
                userVO.setName(name);
                userVO.setId(++sequence);
                userVO.setGrade("normal");
                userList.put(userVO,new ArrayList<ItemVO>());
                System.out.println(userVO.getName() + "님 회원가입 완료되었습니다.");
                break;
            }
            else {
                System.out.println("이름이 올바르지 않습니다");
            }
        }
    }

    public Map<UserVO, List<ItemVO>> getUserList() {
        return userList;
    }

    public void printUserList() {
        for (UserVO user : userList.keySet()) {
            System.out.println("회원이름 : " + user.getName());
            System.out.println("회원번호 : " + user.getId());
        }
    }
}
