package com.example.kim_2WEEK.item;

import com.example.kim_2WEEK.user.UserDAO;
import com.example.kim_2WEEK.user.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAO {
    UserDAO userDAO = new UserDAO();
    Map<UserVO, List<ItemVO>> userList = userDAO.getUserList();
    public void addItem(String name,String itemName, int itemPrice) {
        for (UserVO user : userList.keySet()) {
            if(user.getName().equals(name)) {
                ItemVO itemVO = new ItemVO();
                itemVO.setItemName(itemName);
                itemVO.setItemPrice(itemPrice);
                itemVO.setOrderNum(userList.get(user).size()+1);
                userList.get(user).add(itemVO);
            }
        }
    }

    public void printAllItemList() {

        for (UserVO user : userList.keySet()) {
            System.out.println("회원 이름: " + user.getName());
            for (ItemVO userItem : userList.get(user)) {
                System.out.println("상품 이름: " + userItem.getItemName());
                System.out.println("상품 가격: " + userItem.getItemPrice());
            }
        }
    }

    public void printItemList(String userName) {
        Map<UserVO, List<ItemVO>> userList = userDAO.getUserList();
        for (UserVO user : userList.keySet()) {
            if (user.getName().equals(userName)) {
                System.out.println("회원 이름: " + user.getName());
                for(ItemVO userItem : userList.get(user)) {
                    System.out.println("상품 이름: " + userItem.getItemName());
                    System.out.println("상품 가격: " + userItem.getItemPrice());
                }
            }
        }
    }
}
