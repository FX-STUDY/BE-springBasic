package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.MemoryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    ItemService itemService;


    @BeforeEach
    void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        itemService = appConfig.itemService();
        itemService = new ItemServiceImpl(new MemoryItemRepository());

    }
    @Test
    void add() {
        //상품 등록
        //given
        Item item = new Item("HTTP BOOK", 10000, 10); //상품명, 가격, 수량

        //when
        itemService.addItem(item);
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(item).isEqualTo(findItem);
    }



    @Test
    void findItem() {
    }
}