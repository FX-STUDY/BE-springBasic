package hello.itemservice.controller;

import hello.itemservice.domain.Item;
import hello.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ItemService itemService;
    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Item> findAll = itemService.findAll();
        model.addAttribute("findAll", findAll);
        System.out.println("MODEL : "+model);
        return "home";
    }
}
