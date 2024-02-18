package hello.itemservice.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemForm {

    private String itemName;
    private int price;
    private int quantity;
}
