package com.szincho.kimhyungjunproject.Order.DTO.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {
    String destination;
    List<OrderFoodResponse> foods = new ArrayList<>();
    long totalPrice;

    public void addOrderFoodResponse(OrderFoodResponse orderedFood) {
        this.foods.add(orderedFood);
    }
}