package com.myMission.yebin.item;

public interface ItemDAOInterface {
    public abstract void saveItem(String itemName, int itemPrice);
    public abstract Integer findItemPriceByItemName(String itemName);
}
