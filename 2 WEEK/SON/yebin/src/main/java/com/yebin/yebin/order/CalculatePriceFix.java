package com.yebin.yebin.order;

public class CalculatePriceFix implements CalculatePrice{
    @Override
    public int calculatePrice(int itemPrice, int discount) {
        return itemPrice - discount;
    }
}
