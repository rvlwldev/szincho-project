package com.szincho.kimhyungjunproject.Order.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderedFoodResponse {
    String name;
    int count;
    String description;
}
