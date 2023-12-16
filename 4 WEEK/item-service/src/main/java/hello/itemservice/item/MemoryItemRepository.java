package hello.itemservice.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

    @Override
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

}
