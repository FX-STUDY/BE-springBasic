package com.myMission.yebin.item;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDAO {
    private Map<String, Integer> itemList = new HashMap<>();
    
    // 상품 데이터 저장
    public void saveItem(String itemName, int itemPrice){
        ItemVO itemVO = new ItemVO(itemName, itemPrice);
        itemList.put(itemVO.getItemName(), itemVO.getItemPrice());
    }

    // 상품 이름으로 상품 가격 찾기
    public int findItemPriceByItemName(String itemName){
        int findItemPrice;
        for (ItemVO itemVO : itemList){
            if (itemName.equals(itemVO.getItemName())){
                findItemPrice = itemVO.getItemPrice();
                return findItemPrice;
            }
        }
        return 0;
    }

}
