package uku.java.Testing;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order);
    Optional<Order> getOrderById(int id);
}
