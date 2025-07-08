package uku.java;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import uku.java.Testing.Order;
import org.mockito.junit.jupiter.MockitoExtension;
import uku.java.Testing.OrderRepository;
import uku.java.Testing.OrderService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    public void processOrderPositiveTest(){
        Order order = new Order(1, "Latte", 2, 350.0);

        Mockito.when(orderRepository.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);
        Assertions.assertEquals("Order processed successfully", result);

        Mockito.verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void processOrderNegativeTest(){
        Order order = new Order(1, "Latte", 2, 350.0);

        Mockito.when(orderRepository.saveOrder(order)).thenReturn(0);

        String result = orderService.processOrder(order);
        Assertions.assertEquals("Order processing failed", result);

        Mockito.verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void processOrderThrowTest(){
        Order order = new Order(1, "Latte", 2, 350.0);

        Mockito.doThrow(new RuntimeException("Something went wrong")).when(orderRepository).saveOrder(order);
        Assertions.assertThrows(RuntimeException.class, () -> orderService.processOrder(order),
                "Expected an exception when saving the order.");

        Mockito.verify(orderRepository, Mockito.times(1)).saveOrder(order);
    }

    @Test
    public void calculateTotalPositiveTest(){
        Order order = new Order(1, "Latte", 2, 350.0);

        Mockito.when(orderRepository.getOrderById(order.getId())).thenReturn(Optional.of(order));
        double result = orderService.calculateTotal(order.getId());
        Assertions.assertEquals(700.0, result, 0.001);

        Mockito.verify(orderRepository, Mockito.times(1)).getOrderById(order.getId());
    }

    @Test
    public void calculateTotalNegativeTest(){
        Order order = new Order(1, "Latte", 2, 350.0);

        Mockito.when(orderRepository.getOrderById(order.getId())).thenReturn(Optional.empty());
        Assertions.assertEquals(0.0, orderService.calculateTotal(order.getId()), 0.001);

        Mockito.verify(orderRepository, Mockito.times(1)).getOrderById(order.getId());
    }

    @Test
    public void calculateTotalByZeroQuantityTest(){
        Order order = new Order(1, "Latte", 0, 350.0);

        Mockito.when(orderRepository.getOrderById(order.getId())).thenReturn(Optional.of(order));
        double result = orderService.calculateTotal(order.getId());
        Assertions.assertEquals(0.0, result, 0.001);

        Mockito.verify(orderRepository, Mockito.times(1)).getOrderById(order.getId());
    }
}
