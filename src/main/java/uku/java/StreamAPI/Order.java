package uku.java.StreamAPI;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Order {
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return String.format("Order [id = %s, orderDate = %s, deliveryDate = %s, status = %s, products = %s]",
                id, orderDate, deliveryDate, status, products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
               orderDate.isEqual(order.orderDate) &&
                deliveryDate.isEqual(order.deliveryDate) &&
                Objects.equals(status, order.status) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, orderDate, deliveryDate, status, products);
    }

    public static class Builder {
        Order order = new Order();

        public Builder() {
            order = new Order();
        }

        public Builder id(Long id) {
            order.id = id;
            return this;
        }

        public Builder orderDate(LocalDate orderDate) {
            order.orderDate = orderDate;
            return this;
        }

        public Builder deliveryDate(LocalDate deliveryDate) {
            order.deliveryDate = deliveryDate;
            return this;
        }

        public Builder status(String status) {
            order.status = status;
            return this;
        }
        public Builder products(Set<Product> products) {
            order.products = products;
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
