package uku.java.JavaCore.StreamAPI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Products
        Product latte = new Product.Builder()
                .id(1L)
                .name("Latte")
                .category("Classic drink")
                .price(new BigDecimal("320"))
                .build();

        Product americano = new Product.Builder()
                .id(2L)
                .name("Americano")
                .category("Classic drink")
                .price(new BigDecimal("250"))
                .build();

        Product cappuccino = new Product.Builder()
                .id(3L)
                .name("Cappuccino")
                .category("Classic drink")
                .price(new BigDecimal("320"))
                .build();

        Product espresso = new Product.Builder()
                .id(4L)
                .name("Espresso")
                .category("Classic drink")
                .price(new BigDecimal("300"))
                .build();

        Product tiramisu = new Product.Builder()
                .id(5L)
                .name("Tiramisu")
                .category("Yummy menu")
                .price(new BigDecimal("205"))
                .build();

        Product croissant = new Product.Builder()
                .id(6L)
                .name("Croissant")
                .category("Yummy menu")
                .price(new BigDecimal("155"))
                .build();

        Product cheesecake = new Product.Builder()
                .id(7L)
                .name("Cheesecake")
                .category("Yummy menu")
                .price(new BigDecimal("170"))
                .build();

        //Orders
        Order first = new Order.Builder()
                .id(1L)
                .orderDate(LocalDate.of(2024, Month.JULY, 6))
                .deliveryDate(LocalDate.of(2024, Month.JULY, 6))
                .status(Status.IN_PREPARATION.getDescription())
                .products(new HashSet<>(Arrays.asList(latte, cheesecake)))
                .build();

        Order second = new Order.Builder()
                .id(2L)
                .orderDate(LocalDate.of(2024, Month.FEBRUARY, 1))
                .deliveryDate(LocalDate.of(2024, Month.FEBRUARY, 1))
                .status(Status.IN_PREPARATION.getDescription())
                .products(new HashSet<>(Arrays.asList(latte, tiramisu, croissant)))
                .build();

        Order third = new Order.Builder()
                .id(3L)
                .orderDate(LocalDate.of(2024, Month.APRIL, 1))
                .deliveryDate(LocalDate.of(2024, Month.APRIL, 1))
                .status(Status.READY.getDescription())
                .products(new HashSet<>(Arrays.asList(americano, cheesecake)))
                .build();

        Order fourth = new Order.Builder()
                .id(4L)
                .orderDate(LocalDate.of(2024, Month.MARCH, 15))
                .deliveryDate(LocalDate.of(2024, Month.MARCH, 15))
                .status(Status.ISSUED.getDescription())
                .products(new HashSet<>(Arrays.asList(espresso, tiramisu)))
                .build();

        Order fifth = new Order.Builder()
                .id(5L)
                .orderDate(LocalDate.of(2024, Month.MARCH, 14))
                .deliveryDate(LocalDate.of(2024, Month.MARCH, 14))
                .status(Status.ISSUED.getDescription())
                .products(new HashSet<>(Arrays.asList(americano, cheesecake)))
                .build();

        Order sixth = new Order.Builder()
                .id(6L)
                .orderDate(LocalDate.of(2024, Month.MARCH, 14))
                .deliveryDate(LocalDate.of(2024, Month.MARCH, 14))
                .status(Status.ACCEPTED.getDescription())
                .products(new HashSet<>(Arrays.asList(espresso, tiramisu)))
                .build();

        Order seventh = new Order.Builder()
                .id(7L)
                .orderDate(LocalDate.of(2024, Month.FEBRUARY, 15))
                .deliveryDate(LocalDate.of(2024, Month.FEBRUARY, 15))
                .status(Status.IN_PROGRESS.getDescription())
                .products(new HashSet<>(Arrays.asList(americano, latte)))
                .build();

        Order eighth = new Order.Builder()
                .id(8L)
                .orderDate(LocalDate.of(2024, Month.FEBRUARY, 15))
                .deliveryDate(LocalDate.of(2024, Month.FEBRUARY, 15))
                .status(Status.READY.getDescription())
                .products(new HashSet<>(Arrays.asList(americano, espresso, tiramisu)))
                .build();

        Order ninth = new Order.Builder()
                .id(9L)
                .orderDate(LocalDate.of(2024, Month.JUNE, 12))
                .deliveryDate(LocalDate.of(2024, Month.JUNE, 12))
                .status(Status.IN_PROGRESS.getDescription())
                .products(new HashSet<>(Arrays.asList(latte, espresso, croissant)))
                .build();

        Order tenth = new Order.Builder()
                .id(10L)
                .orderDate(LocalDate.of(2024, Month.JUNE, 13))
                .deliveryDate(LocalDate.of(2024, Month.JUNE, 13))
                .status(Status.READY.getDescription())
                .products(new HashSet<>(Arrays.asList(cappuccino, croissant)))
                .build();

        //Customers
        Customer Olivia = new Customer.Builder()
                .id(1L)
                .name("Olivia")
                .level(1L)
                .orders(new HashSet<>(Arrays.asList(first, second, seventh, eighth, tenth)))
                .build();

        Customer Liam = new Customer.Builder()
                .id(2L)
                .name("Liam")
                .level(2L)
                .orders(new HashSet<>(Arrays.asList(second, third, fourth, sixth, ninth)))
                .build();

        Customer Sophia = new Customer.Builder()
                .id(3L)
                .name("Sophia")
                .level(3L)
                .orders(new HashSet<>(Arrays.asList(second, fourth, sixth, ninth, tenth)))
                .build();

        Customer Amelia = new Customer.Builder()
                .id(4L)
                .name("Amelia")
                .level(2L)
                .orders(new HashSet<>(Arrays.asList(first, third, fifth, sixth, ninth, tenth)))
                .build();

        Customer William = new Customer.Builder()
                .id(5L)
                .name("William")
                .level(2L)
                .orders(new HashSet<>(Arrays.asList(first, third, seventh, eighth, tenth)))
                .build();

        List<Customer> customers = new ArrayList<>(Arrays.asList(Olivia, Liam, Sophia, Amelia, William));

        //1. Получите список продуктов из категории "Yummy menu" с ценой более 200
        System.out.println("1.");
        List<Product> products = customers.stream()
                .flatMap(orders -> orders.getOrders().stream())
                .flatMap(product -> product.getProducts().stream())
                .filter(category -> category.getCategory().equals("Yummy menu") &&
                        category.getPrice().compareTo(BigDecimal.valueOf(200)) >= 0)
                .distinct()
                .toList();

        products.forEach(System.out::println);

        //2. Получите список заказов с продуктами из категории "Classic drink"
        System.out.println("2.");
        List<Order> orders = customers.stream()
                .flatMap(order -> order.getOrders().stream())
                .filter(product -> product.getProducts().stream()
                        .anyMatch(category -> category.getCategory().equals("Classic drink")))
                .distinct()
                .toList();

        orders.forEach(System.out::println);

        //3. Получите список продуктов из категории "Classic drink" и примените скидку 10% и получите сумму всех продуктов
        System.out.println("3.");
        BigDecimal sumProductsClassicDrink = customers.stream()
               .flatMap(customer -> customer.getOrders().stream())
               .flatMap(order -> order.getProducts().stream())
               .filter(category -> category.getCategory().equals("Classic drink"))
               .distinct()
               .map(price -> price.getPrice().multiply(new BigDecimal("0.9")))
               .reduce(BigDecimal.ZERO, BigDecimal::add);

       System.out.println(sumProductsClassicDrink);

        //4. Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2024 и 01-апр-2024
        System.out.println("4.");
        List<Product> productsOfSecondLevel = customers.stream()
                .filter(customer -> customer.getLevel().equals(2L))
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2024, Month.FEBRUARY, 1))
                && order.getOrderDate().isBefore(LocalDate.of(2024, Month.APRIL, 1)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();

        productsOfSecondLevel.forEach(System.out::println);

        //5. Получите топ 2 самые дешевые продукты из категории "Yummy menu"
        System.out.println("5.");
        List<Product> productsOfCheap = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(category -> category.getCategory().equals("Yummy menu"))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();

        productsOfCheap.forEach(System.out::println);

        //6. Получите 3 самых последних сделанных заказа
        System.out.println("6.");
        List<Order> ordersOfTreeLast = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();

        ordersOfTreeLast.forEach(System.out::println);

        //7. Получите список заказов, сделанных 15-марта-2024, выведите id заказов в консоль и затем верните список
        // их продуктов
        System.out.println("7.");
        List<Order> ordersFifteenthMarch = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2024, Month.MARCH, 15)))
                .distinct()
                .peek(id -> System.out.println("Id:" + id.getId()))
                .toList();

        ordersFifteenthMarch.forEach(System.out::println);

        //8. Рассчитайте общую сумму всех заказов, сделанных в феврале 2024
        System.out.println("8.");
        List<BigDecimal> listAmountForFebruary = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> YearMonth.from(order.getOrderDate()).equals(YearMonth.of(2024, 2)))
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .toList();

        int amountForFebruary = listAmountForFebruary.stream()
                .mapToInt(BigDecimal::intValue)
                .sum();

        System.out.println(amountForFebruary);

        //9. Рассчитайте средний платеж по заказам, сделанным 14-марта-2024
        System.out.println("9.");
        List<BigDecimal> orderForFourteenthOfMarch = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2024, Month.MARCH, 14)))
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .toList();

        double averagePayment = orderForFourteenthOfMarch.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        System.out.println(averagePayment);

        //10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех
        //продуктов категории "Classic drink"
        System.out.println("10.");
        List<BigDecimal> productClassicDrink = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(category -> category.getCategory().equals("Classic drink"))
                .map(Product::getPrice)
                .toList();

        System.out.println("Sum");
        int sumProductClassicDrink = productClassicDrink.stream()
                .mapToInt(BigDecimal::intValue)
                .sum();

        System.out.println(sumProductClassicDrink);

        System.out.println("Average");
        double averageProductClassicDrink = productClassicDrink.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        System.out.printf("%.2f%n", averageProductClassicDrink);

        System.out.println("Maximum");
        long maxProductClassicDrink = productClassicDrink.stream()
                .mapToLong(BigDecimal::longValue)
                .max()
                .orElse(0L);

        System.out.println(maxProductClassicDrink);

        System.out.println("Minimum");
        long minProductClassicDrink = productClassicDrink.stream()
                .mapToLong(BigDecimal::longValue)
                .min()
                .orElse(0L);

        System.out.println(minProductClassicDrink);

        System.out.println("Quantity");
        long countProductClassicDrink = productClassicDrink.stream()
                .mapToLong(BigDecimal::longValue)
                .count();

        System.out.println(countProductClassicDrink);
        //11. Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе
        System.out.println("11.");
        Map<Long, Integer> ordersMap = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(Order::getId, x -> x.getProducts().size()));

        ordersMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов
        System.out.println("12.");
        Map<Customer, List<Order>> customersMap = customers.stream()
                .collect(Collectors.toMap(x -> x, x -> new ArrayList<>(x.getOrders())));

        customersMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа
        System.out.println("13.");
        Map<Order, Double> allAmountProducts = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(x -> x, x -> x.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .doubleValue()));

        allAmountProducts.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        //14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории
        System.out.println("14.");
        Map<String, List<String>> listProducts = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        listProducts.forEach((key, value) -> System.out.println("Category: " + key + ", Products: " + value));

        //15. Получите Map<String, Product> → самый дорогой продукт по каждой категории
        System.out.println("15.");
        Map<String, Product> mostExpensiveProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .max(Comparator.comparing(Product::getPrice)).orElse(null)));

        mostExpensiveProduct.forEach((key, value) -> System.out.println("Category: " + key + ", Product: " + value));
    }
}
