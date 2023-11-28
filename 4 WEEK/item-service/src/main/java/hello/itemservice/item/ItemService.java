package hello.itemservice.item;

public interface ItemService {
    void join(Item item);
    void updateItem(Item item);
    Item findItem(Long itemId);
}
