package com.yebin.yebin.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
    float calculatePrice(int itemPrice, float discountPrice);
}
