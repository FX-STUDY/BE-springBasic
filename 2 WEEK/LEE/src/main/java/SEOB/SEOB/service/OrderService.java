package SEOB.SEOB.service;


import SEOB.SEOB.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String name, int price);
    Order calculatePrice();
}
