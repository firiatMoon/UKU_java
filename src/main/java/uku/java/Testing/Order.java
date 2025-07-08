package uku.java.Testing;

public class Order {
    private final int id;
    private final String productName;
    private final int quantity;
    private final double unitPrice;

//    public Order() {}

    public Order(int id, String productName, int quantity, double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice(){
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return String.format("Order [id = %s, productName = %s, quantity = %s, unitPrice = %s",
                id, productName, quantity, unitPrice);
    }
}
