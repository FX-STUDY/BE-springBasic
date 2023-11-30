package hello.itemservice.repository;

import hello.itemservice.domain.Item;

public interface ItemRepository {

    void save(Item item);

    Item findByItemId(Long itemId);
}
