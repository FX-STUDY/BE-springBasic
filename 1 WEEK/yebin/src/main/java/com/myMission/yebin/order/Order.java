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
        ㄴ
        // 상품 가격 = (itemDAO에서 상품Name으로 가격을 찾아온것) - (회원Id로 등급 찾아와서 해당하는 할인가격가져온 것)
        Integer itemPrice = itemDAO.findItemPriceByItemName(itemName)
                                - discount.setDiscountByGrade(userDAO.findGradeByUserId(userId));

        System.out.println(userId + "님이 "+itemName+"을 주문했습니다. 가격 : " +itemPrice);

    }

}
