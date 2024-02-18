package hello.core.order;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Order {

    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;


    public int calculatePrice(){
        return itemPrice - discountPrice;
    }

    public int getDiscountPrice(){
        return discountPrice;
    }

}
