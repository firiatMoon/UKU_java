package uku.java.JavaCore.StreamAPI;

import java.util.Objects;
import java.util.Set;

public class Customer {
    private Long id;
    private String name;
    private Long level;
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLevel() {
        return level;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return String.format("Customer [id = %s, name = %s, level = %s, orders = %s]", id, name, level, orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(level, customer.level) &&
                Objects.equals(orders, customer.orders);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, name, level, orders);
    }

    public static class Builder {
        private final Customer customer;

        public Builder() {
            customer = new Customer();
        }

        public Builder id(Long id) {
            customer.id = id;
            return this;
        }

        public Builder name(String name) {
            customer.name = name;
            return this;
        }

        public Builder level(Long level) {
            customer.level = level;
            return this;
        }

        public Builder orders(Set<Order> orders) {
            customer.orders = orders;
            return this;
        }
        public Customer build() {
            return customer;
        }
    }
}
