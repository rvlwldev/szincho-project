package com.szincho.kimhyungjunproject.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository repo;

    public OrderService(OrderRepository orderRepository) {
        this.repo = orderRepository;
    }
}
