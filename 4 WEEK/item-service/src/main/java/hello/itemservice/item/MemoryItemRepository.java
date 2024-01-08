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
    public Item save(Item item) {
        item.setId(++SEQUENCE);
        store.put(item.getId(),item);
        return item;
    }

    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public void update(Long itemId, Item updatedItem) {
        Item findItem = findById(itemId);
        findItem.setName(updatedItem.getName());
        findItem.setPrice(updatedItem.getPrice());
        findItem.setQuantity(updatedItem.getQuantity());
    }

    @Override
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore(){
        store.clear();
        SEQUENCE = 0L;
    }

}
