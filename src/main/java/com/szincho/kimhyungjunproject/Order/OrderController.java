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
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final FoodService foodService;
    private final OrderRequestMapper mapper;

    @Autowired
    public OrderController(OrderService service, FoodService foodService, OrderRequestMapper mapper) {
        this.service = service;
        this.foodService = foodService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> receiveOrder(@RequestBody @Valid OrderRequest request) throws FoodNotFoundException {
        HashMap<Integer, Integer> CountWithFoodMap = mapper.getCountWithFoodMap(request);
        Order order = mapper.getOrderByRequestedDto(request, foodService);

        OrderResponse response = service.saveOrder(order, CountWithFoodMap);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<OrderResponse> getAllOrderHistory() {
        return service.getAllOrderHistoryDESC();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable("id") long id) throws IllegalOrderException, OrderNotFoundException {
        service.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/depart/{id}")
    public ResponseEntity<OrderResponse> departOrder(@PathVariable("id") long id) throws IllegalOrderException, OrderNotFoundException {
        service.departOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/arrive/{id}")
    public ResponseEntity<OrderResponse> arriveOrder(@PathVariable("id") long id) throws IllegalOrderException, OrderNotFoundException {
        service.arriveOrder(id);
        return ResponseEntity.noContent().build();
    }

}
