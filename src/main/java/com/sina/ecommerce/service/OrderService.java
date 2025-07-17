package com.sina.ecommerce.service;

import com.sina.ecommerce.model.Order;
import com.sina.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
