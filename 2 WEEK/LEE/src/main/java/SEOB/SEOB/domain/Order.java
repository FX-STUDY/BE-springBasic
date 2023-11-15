package SEOB.SEOB.domain;

public class Order {

    private Long id;
    private String name;
    private int price;
    private int discountedPrice;

    public Order(Long id, String name, int price, int discountedPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public int calculatePrice() {
        return price - discountedPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
