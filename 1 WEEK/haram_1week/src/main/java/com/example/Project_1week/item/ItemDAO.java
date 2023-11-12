package com.example.Project_1week.item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static List<ItemVO> itemList = new ArrayList<ItemVO>();
    public List<ItemVO> getItemList() {

        for(ItemVO item : itemList) {

            System.out.println("상품이름 : " + item.getItemName());
            System.out.println("상품가격 : " + item.getItemPrice());

        }
        return itemList;
    }

    public void setItem(ItemVO vo) {

        itemList.add(vo);
    }
}
