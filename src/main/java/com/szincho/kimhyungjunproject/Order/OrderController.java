package com.szincho.kimhyungjunproject.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @Autowired
    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

}
