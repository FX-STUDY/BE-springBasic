package hello.itemservice;

import hello.itemservice.item.Item;
import hello.itemservice.item.ItemRepository;
import hello.itemservice.item.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {
    private ItemRepository itemRepository = new MemoryItemRepository();

    @AfterEach
    void afterLogic(){
        itemRepository.clearStore();
    }

}
