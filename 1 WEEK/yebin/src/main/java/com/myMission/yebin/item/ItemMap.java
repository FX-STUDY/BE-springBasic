package com.myMission.yebin.item;

import java.util.HashMap;
import java.util.Map;

public class ItemMap {
    private static Map<String, Integer> itemList;
    public static Map<String, Integer> getInstance(){
        if (itemList == null) {
            itemList = new HashMap<>();
        }
        return itemList;
    }
}
