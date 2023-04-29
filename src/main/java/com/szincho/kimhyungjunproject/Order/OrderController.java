package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import com.szincho.kimhyungjunproject.Food.FoodService;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.IllegalOrderException;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.OrderNotFoundException;
import com.szincho.kimhyungjunproject.Order.DTO.Mapper.OrderRequestMapper;
import com.szincho.kimhyungjunproject.Order.DTO.Request.OrderRequest;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final FoodService foodService;

    @Autowired
    public OrderController(OrderService service, FoodService foodService) {
        this.service = service;
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> receiveOrder(@RequestBody @Valid OrderRequest request) throws FoodNotFoundException {
        HashMap<Integer, Integer> CountWithFoodMap = OrderRequestMapper.MAPPER.getCountWithFoodMap(request);
        Order order = OrderRequestMapper.MAPPER.getOrderByRequestedDto(request, foodService);

        OrderResponse response = service.saveOrder(order, CountWithFoodMap);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable("id") long id) throws IllegalOrderException, OrderNotFoundException {
        service.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

}
