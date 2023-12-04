package hello.itemservice.controller;


import hello.itemservice.domain.Item;
import hello.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/addItem")
    public String addItemForm() {
        return "/addItemForm";
    }

    @PostMapping("/addItem")
    public String addItem(ItemForm form) {
        //생성자로 주입
        Item item = new Item(
                form.getItemName(),
                form.getPrice(),
                form.getQuantity()
        );

        itemService.addItem(item);

        return "redirect:/";
    }
}
