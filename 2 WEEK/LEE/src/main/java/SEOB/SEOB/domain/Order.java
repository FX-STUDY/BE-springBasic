package SEOB.SEOB.domain;

public class Order {

    private Long id;
    private String name;
    private int price;
    private int discountPrice;

    public Order(Long id, String name, int price, int discountPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return price - discountPrice;
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

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountedPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
}
