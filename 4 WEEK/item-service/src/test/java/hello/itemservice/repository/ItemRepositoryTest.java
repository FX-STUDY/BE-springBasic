package hello.itemservice.repository;

import hello.itemservice.AppConfig;
import hello.itemservice.domain.Item;
import hello.itemservice.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemService itemService;
    ItemRepository itemRepository;
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        itemService = appConfig.itemService();

        itemRepository = new MemoryItemRepository();

    }
    @Test
    void findAll() {
        //상품 등록
        //given
        Item item1 = new Item("HTTP BOOK", 10000, 10); //상품명, 가격, 수량
        Item item2 = new Item("JPA BOOK", 43000, 5); //상품명, 가격, 수량
        Item item3 = new Item("Spring BOOK", 20000, 100); //상품명, 가격, 수량

        //when
        itemService.addItem(item1);
        itemService.addItem(item2);
        itemService.addItem(item3);
        Item findItem = itemService.findItem(1L);

        //then
        List<Item> allItems = itemRepository.findAll();
        assertThat(allItems).contains(item1, item2, item3);
    }
}