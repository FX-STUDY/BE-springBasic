package hello.itemservice.item;

import java.util.HashMap;
import java.util.Map;

public class MemoryItemRepository implements ItemRepository {
    private static Map<Long, Item> store = new HashMap<>();
    private static long SEQUENCE = 0L;
}