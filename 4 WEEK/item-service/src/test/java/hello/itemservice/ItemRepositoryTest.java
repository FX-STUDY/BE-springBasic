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

    @Test
    void saveTest(){
        //given
        Item item = new Item("itemA",1000,10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAllTest(){
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",2000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1,item2);
    }

    @Test
    void updateTest(){
        //given
        Item item = new Item("item1",10000,10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateItem = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateItem);

        Item findItem = itemRepository.findById(itemId);

        //then
        assertThat(findItem.getName()).isEqualTo(updateItem.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}
