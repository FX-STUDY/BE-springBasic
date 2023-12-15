package hello.itemservice.service;

import hello.itemservice.domain.Item;

import java.util.List;

public interface ItemService {

    //상품 목록 추가
    void addItem(Item item);

    //상품상세
    Item findItem(Long itemId);

    List<Item> findAll();
}


