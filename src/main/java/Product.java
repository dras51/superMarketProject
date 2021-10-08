public class Product {
    private int id;
    private int price;
    private int buyingPrice;
    private String productName;
    private int quantity;

    Product(int id, String productName, int price, int buyingPrice, int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void sellProduct(int quantity) {
        this.quantity -= quantity;
    }

    public String toString() {
        return "------Product id: " + id + "-------\n" +
                "Product Name: " + productName +
                "\nPrice: " + price +
                "\nQuantity: " + quantity;
    }

}
