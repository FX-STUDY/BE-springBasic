package com.myMission.yebin.item;


public class ItemVO {
    String itemName;
    Integer itemPrice;

    public ItemVO(String itemName, int itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }
}
