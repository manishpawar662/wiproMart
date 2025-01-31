package com.wipro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.WiproMartApplication;
import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Order;
import com.wipro.wipromart.entity.OrderItem;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.OrderRepository;
import com.wipro.wipromart.service.OrderService;
import com.wipro.wipromart.service.OrderServiceImpl;

@SpringBootTest(classes = {WiproMartApplication.class})
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @Mock
    private OrderRepository orderRepository;

    @Test
    void testGetOrderById() {
        //sample product
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Product1");
        product.setProductPrice(2000.0);
        product.setMfd(LocalDate.of(2025, 1, 1));
        product.setCategory("Electronics");

        //sample order items
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(1);
        orderItem.setQty(2);
        orderItem.setItemTotal(4000.0);
        orderItem.setProduct(product);

        //sample customer
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setMobile("1234567890");
        customer.setCity("Mumbai");

        //sample order
        Order order = new Order();
        order.setOrderId(100);
        order.setOrderDate(LocalDate.of(2025, 1, 10));
        order.setOrderAmount(4000.0);
        order.setOrderStatus("Pending");
        order.setOrderItems(Arrays.asList(orderItem));
        order.setCustomer(customer);

        Optional<Order> optionalOrder = Optional.of(order);

        // Mock repository behavior
        when(orderRepository.findById(100)).thenReturn(optionalOrder);

        // Test service method
        Order actualOrder = orderService.getOrderDetails(100);

        assertEquals(100, actualOrder.getOrderId());
        assertEquals("Pending", actualOrder.getOrderStatus());
        assertEquals(4000.0, actualOrder.getOrderAmount());
        assertEquals(1, actualOrder.getOrderItems().size());
        assertEquals("John", actualOrder.getCustomer().getFirstName());
    }

    @Test
    void testGetOrderByIdWithException() {
        // Mock repository to throw exception
        when(orderRepository.findById(100)).thenThrow(ResourceNotFoundException.class);

        // Test service method for exception
        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderDetails(100));
    }
}
