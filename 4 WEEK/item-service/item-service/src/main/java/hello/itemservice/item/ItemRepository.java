package hello.itemservice.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item findById(Long id) {
        return store.get(id);
    }

    public void saveItem(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);
    }

    public List<Item> findAllItem() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        sequence = 0L;
        store.clear();
    }
}
