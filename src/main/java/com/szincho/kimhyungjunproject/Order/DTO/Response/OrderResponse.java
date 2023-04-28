package com.szincho.kimhyungjunproject.Order.DTO.Response;

import java.util.List;

public class OrderResponse {
    String destination;
    List<OrderedFoodResponse> foods;
    int totalCount;
}
