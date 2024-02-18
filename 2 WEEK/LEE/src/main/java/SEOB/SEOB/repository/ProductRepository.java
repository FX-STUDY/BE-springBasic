package SEOB.SEOB.repository;


import SEOB.SEOB.domain.Order;

public interface ProductRepository {
    public Order save(Long memberId, Order order);


}
