package com.yebin.yebin.order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private float discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, float discountPrice){
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public float calculatePrice(){
        return itemPrice - discountPrice;
    }

    public float getDiscountPrice(){
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
