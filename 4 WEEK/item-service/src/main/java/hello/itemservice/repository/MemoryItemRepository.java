package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryItemRepository implements ItemRepository{
    private static ConcurrentHashMap<Long, Item> store = new ConcurrentHashMap<>();

    private static long SEQUENCE = 0L;

    //상품 등록 (상품명, 가격, 수량)
    public void save(Item item) {
        item.setItemId(++SEQUENCE);
        store.put(item.getItemId(), item);
    }

    //상품 상세
    public Item findByItemId(Long itemId) {
        return store.get(itemId);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

}
