package com.szincho.kimhyungjunproject.Order.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderFoodResponse {
    String name;
    int count;
    long price;
}
