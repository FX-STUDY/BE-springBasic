package hello.itemservice.item;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
}
