package com.myMission.yebin.item;


import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private List<ItemVO> itemList = new ArrayList<>();
    
    // 상품 데이터 저장
    public void saveItem(String itemName, int itemPrice){
        ItemVO itemVO = new ItemVO(itemName, itemPrice);
        itemList.add(itemVO);
    }

}
