package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import com.szincho.kimhyungjunproject.Order.DTO.Mapper.OrderResponseMapper;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public OrderResponse saveOrder(Order order, HashMap<Integer, Integer> CountWithFoodMap) throws FoodNotFoundException {
        validateOrderFoods(order);
        return OrderResponseMapper.MAPPER.toDto(orderRepo.save(order), CountWithFoodMap);
    }

    private void validateOrderFoods(Order order) throws FoodNotFoundException {

    }

}
