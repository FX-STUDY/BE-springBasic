package hello.itemservice.repository;

import hello.itemservice.domain.Item;

public interface ItemRepository {

    public void save(Item item);
    public Item findByItemId(Long itemId);
}
