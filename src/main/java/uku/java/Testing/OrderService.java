package uku.java.Testing;

import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        int result = orderRepository.saveOrder(order);
        String message = null;
        try {
            if (result > 0){
                message = "Order processed successfully";
            } else {
                message = "Order processing failed";
                throw new RuntimeException("Something went wrong");
            }
        } catch (RuntimeException ex) {
            message = ex.getMessage();
        }
        return message;
    }

    public double calculateTotal(int id) {
        Optional<Order> order = orderRepository.getOrderById(id);
        return order.map(Order::getTotalPrice).orElse(0.0);
    }
}
