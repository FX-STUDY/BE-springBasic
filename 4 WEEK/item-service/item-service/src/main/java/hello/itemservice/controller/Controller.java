package hello.itemservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/itemList")
    public String itemList() {
        return "itemList";
    }
    @GetMapping("/itemAddForm")
    public String itemAdd() {
        return "itemAddForm";
    }
}
