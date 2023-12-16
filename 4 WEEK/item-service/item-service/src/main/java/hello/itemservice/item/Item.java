package hello.itemservice.item;

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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }
}
