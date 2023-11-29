package hello.itemservice.item;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void join(Item item) {
        itemRepository.save(item);
    }


    @Override
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }
}
