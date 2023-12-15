package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //상품 목록 추가

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    //상품 상세
    public Item findItem(Long itemId) {
        return itemRepository.findByItemId(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

}
