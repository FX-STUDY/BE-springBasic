package hello.itemservice.item;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
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

    @GetMapping("/itemSave")
    public String getItemSaveForm(){
        return "ItemSaveForm";
    }

    public String itemSave(Item item, Model model){
    @PostMapping("/itemSave")
        itemRepository.save(item);
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("itemList",itemList);
        return "ItemListForm";
    }

    public String getItemUpdateForm(Item item, Model model){
        Item updateItem = itemRepository.findById(item.getId());
        model.addAttribute("updateItem",updateItem);
    @GetMapping("/{itemId}/itemUpdate")
        return "ItemUpdateForm";
    }

    public String itemUpdate(Item item, Model model){
        itemRepository.update(item);
    @PostMapping("/{itemId}/itemUpdate")
        Item updatedItem = itemRepository.findById(item.getId());
        model.addAttribute("item",updatedItem);
        return "ItemDetailForm";
    }
}
