package com.myMission.yebin.order;

import com.myMission.yebin.grade.GradeVO;
import com.myMission.yebin.item.ItemDAO;
import com.myMission.yebin.users.UserDAO;
import com.myMission.yebin.users.UserVO;

public class Order {

    public void ItemOrder(String userName, String itemName){
        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        DiscountPolicy discountPolicy = new DiscountPolicy();
//        discountPolicy.setDiscountByGrade();
//        int itemPrice = itemDAO.findItemPriceByItemName(itemName) *;



    }
    
}
