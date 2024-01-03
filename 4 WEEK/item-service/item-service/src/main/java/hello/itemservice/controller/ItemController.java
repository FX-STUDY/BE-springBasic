package hello.itemservice.controller;

import hello.itemservice.item.Item;
import hello.itemservice.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/hello/item")
@Controller
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/itemList")
    public String itemList(Model model) {
        List<Item> itemList = itemRepository.findAllItem();
        model.addAttribute("itemList",itemList);
        return "itemList";
    }
    @GetMapping("/itemAddForm")
    public String itemAdd() {
        return "itemAddForm";
    }
    @GetMapping("/itemFixForm")
    public String itemFix() {
        return "itemFixForm";
    }

    @PostConstruct
    void init() {
        itemRepository.saveItem(new Item("item1",10000,1));
        itemRepository.saveItem(new Item("item2",20000,2));
    }
}
