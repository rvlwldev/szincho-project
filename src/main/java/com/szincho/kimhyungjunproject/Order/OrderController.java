package com.szincho.kimhyungjunproject.Order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService service;

    public OrderController (OrderService orderService) {
        this.service = orderService;
    }

}
