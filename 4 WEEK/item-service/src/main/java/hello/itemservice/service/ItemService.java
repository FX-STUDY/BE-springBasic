package hello.itemservice.service;

import hello.itemservice.domain.Item;

public interface ItemService {

    //상품 목록 추가
    void add(Item item);

    //상품상세
    Item findItem(Long itemId);
}
