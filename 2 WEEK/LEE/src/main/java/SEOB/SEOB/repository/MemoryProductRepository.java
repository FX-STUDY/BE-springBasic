package SEOB.SEOB.repository;


import SEOB.SEOB.domain.Order;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryProductRepository implements ProductRepository{

    //memberId, product
    private static ConcurrentHashMap<Long, Order> store = new ConcurrentHashMap<>();
    private static long incrementId = 0L;



    //키 값으로 멤버의 고유 id 저장
    public Order save(Long memberId, Order order) {
        order.setId(++incrementId);


        store.put(memberId, order);
        return order;
    }


}
