package hello.itemservice.item;

import org.springframework.stereotype.Service;


public interface ItemService {
    void join(Item item);
    void updateItem(Item item, String itemName, double itemPrice, long itemQuantity);
    Item findItem(Long itemId);
}
