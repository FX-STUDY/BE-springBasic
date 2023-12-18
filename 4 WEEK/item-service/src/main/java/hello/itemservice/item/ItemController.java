package hello.itemservice.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @GetMapping("/itemListForm")
    public String getItemListForm(Model model){
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("itemList",itemList);
        return "ItemListForm";
    }

    @GetMapping("/{itemId}")
    public String getItemDetailForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "ItemDetailForm";
    }

    @GetMapping("/itemSaveForm")
    public String getItemSaveForm(){
        return "ItemSaveForm";
    }

    @GetMapping("/itemSave")
    public String itemSave(Item item, Model model){
        itemRepository.save(item);
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("itemList",itemList);
        return "ItemListForm";
    }

    @GetMapping("/itemUpdateForm")
    public String getItemUpdateForm(){
        return "ItemUpdateForm";
    }

    @GetMapping("/itemUpdate")
    public String itemUpdate(Item item, Model model){
        itemRepository.update(item);
        Item updatedItem = itemRepository.findById(item.getId());
        model.addAttribute("item",updatedItem);
        return "ItemDetailForm";
    }
}
