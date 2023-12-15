package hello.itemservice.item;

import java.util.HashMap;
import java.util.Map;

public class MemoryItemRepository implements ItemRepository {
    private static Map<Long, Item> store = new HashMap<>();
    private static long SEQUENCE = 0L;
    @Override
    public void save(Item item) {
        item.setId(++SEQUENCE);
        store.put(item.getId(),item);
    }

    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public void update(long itemId, Item updatedItem) {
        store.put(itemId,updatedItem);
    }

}
