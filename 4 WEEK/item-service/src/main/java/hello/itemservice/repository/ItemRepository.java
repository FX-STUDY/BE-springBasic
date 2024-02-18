package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;

public interface ItemRepository {

    void save(Item item);

    Item findByItemId(Long itemId);

    List<Item> findAll();

    void editItem(Long itemId, Item editedItem);
}
