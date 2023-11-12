package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
import com.myMission.yebin.item.ItemMap;
import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;

import java.util.Map;

public class Order {

    private Map<String, Integer> itemMap = ItemMap.getInstance();
    public void ItemOrder(String userId, String itemName){
        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        DiscountPolicy discount = new DiscountPolicy();
        Integer itemPrice = itemDAO.findItemPriceByItemName(itemName)
                                - discount.setDiscountByGrade(userDAO.findGradeByUserId(userId));

        System.out.println(userId + "님이 "+itemName+"을 주문했습니다. 가격 : " +itemPrice);

    }

}
