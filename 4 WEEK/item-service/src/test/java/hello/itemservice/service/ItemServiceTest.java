package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.MemoryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        //then
        List<Item> allItems = itemService.findAll();
        assertThat(allItems).contains(item1, item2, item3);
    }

    @Test
    void editItem() {
        //상품등록
        //given
        Item item1 = new Item("HTTP BOOK", 10000, 10); //상품명, 가격, 수량
        itemService.addItem(item1);

        //when
        itemService.editItem(item1, "JSP BOOK", 5000, 5);

        //then
        Item editedItem = itemService.findItem(item1.getItemId()); // 수정 후 아이템 조회
        assertThat(editedItem.getItemId()).isEqualTo(1);
        assertThat(editedItem.getItemName()).isEqualTo("JSP BOOK");
        assertThat(editedItem.getPrice()).isEqualTo(5000);
        assertThat(editedItem.getQuantity()).isEqualTo(5);

    }
}