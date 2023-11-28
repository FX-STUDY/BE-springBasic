package hello.itemservice.item;

public interface ItemRepository {
    void save(Item item);
    Item findById(Long itemId);
}
