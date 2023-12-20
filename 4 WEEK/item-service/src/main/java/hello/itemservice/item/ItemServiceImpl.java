package hello.itemservice.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public void updateItem(Item item, String itemName, double itemPrice, int itemQuantity) {
        item.setName(itemName);
        item.setPrice(itemPrice);
        item.setQuantity(itemQuantity);
        itemRepository.update(item);
    }

    @Override
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }
}
