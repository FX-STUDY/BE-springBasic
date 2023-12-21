package hello.itemservice.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private long id;
    private String itemName;
    private long itemPrice;
    private long itemCount;


    public Item(String itemName, long itemPrice, long itemCount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

}
