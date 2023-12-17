package hello.itemservice.controller;


import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private ItemService itemService;
    private ItemRepository itemRepository;
    @Autowired
    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
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

    //상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findByItemId(itemId);
        model.addAttribute("item", item);
        return "/itemDetailForm";
    }
}
