package hello.itemservice.service;

import hello.itemservice.domain.Item;

public interface ItemService {

    //상품 목록 추가
    public void add(Item item);

    //상품상세
    public Item findItem(Long itemId);
}
