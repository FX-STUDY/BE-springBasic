package com.myMission.yebin.item;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDAO implements ItemDAOInterface{
    private Map<String, Integer> itemList = ItemMap.getInstance();

    // 상품 데이터 저장
    @Override
    public void saveItem(String itemName, int itemPrice){
        // itemVO는 필요없는 건가??
        itemList.put(itemName,itemPrice);
    }

    // 상품 이름으로 상품 가격 찾기
    @Override
    public Integer findItemPriceByItemName(String itemName){
        return itemList.get(itemName);
    }

}
