package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
import com.myMission.yebin.item.ItemMap;
import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;

import java.util.Map;

public class Order {

    Map<String, Integer> itemMap = ItemMap.getInstance();
    // 상품 주문
    public void ItemOrder(String userId, String itemName){
        UserDAO userDAO = new UserDAO();
        DiscountPolicy discount = new DiscountPolicy();
        Integer findItemPrice = itemMap.get(itemName);
        GradeVO userGrade = userDAO.findGradeByUserId(userId);

        Integer calculatedItemPrice = discount.calculateItemPrice(findItemPrice, userGrade);

        System.out.println(userId + "님이 "+itemName+"을 주문했습니다. 가격 : " +calculatedItemPrice);

    }

}
