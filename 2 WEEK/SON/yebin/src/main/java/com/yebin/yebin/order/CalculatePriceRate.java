package com.yebin.yebin.order;

public class CalculatePriceRate implements CalculatePrice{
    @Override
    public int calculatePrice(int itemPrice, int discount) {
        return itemPrice - itemPrice * (discount / 100) ;
    }
}
