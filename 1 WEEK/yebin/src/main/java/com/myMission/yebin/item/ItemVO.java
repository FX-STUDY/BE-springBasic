package com.myMission.yebin.item;


public class ItemVO {
    String itemName;
    int itemPrice;

    public ItemVO(String itemName, int itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }
}
