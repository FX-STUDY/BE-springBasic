package hello.itemservice.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private String name;
    private double price;
    private Long quantity;
    public void Item(String name, double price, Long quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
