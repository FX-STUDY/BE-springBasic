package hello.itemservice.item;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
    void update(long itemId, Item updatedItem);
    List<Item> findAll();
}
