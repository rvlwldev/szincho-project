package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import com.szincho.kimhyungjunproject.Food.FoodService;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.IllegalOrderException;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.OrderNotFoundException;
import com.szincho.kimhyungjunproject.Order.DTO.Mapper.OrderRequestMapper;
import com.szincho.kimhyungjunproject.Order.DTO.Request.OrderRequest;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Order", description = "주 관련 API")
@Api(tags = "Order")
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

    @Operation(summary = "주문요청", tags = "Order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문성공"),
            @ApiResponse(responseCode = "404", description = "주문음식 ID가 존재하지 않을때")
    })
    @PostMapping
    public ResponseEntity<OrderResponse> receiveOrder(@RequestBody @Valid OrderRequest request) throws FoodNotFoundException {
        HashMap<Integer, Integer> CountWithFoodMap = mapper.getCountWithFoodMap(request);
        Order order = mapper.getOrderByRequestedDto(request, foodService);

        OrderResponse response = service.saveOrder(order, CountWithFoodMap);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "주문내역조회", tags = "Order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 처리(배달)된 주문의 목록을 조회")
    })
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
