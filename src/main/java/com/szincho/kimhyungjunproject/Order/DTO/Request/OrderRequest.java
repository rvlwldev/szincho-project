package com.szincho.kimhyungjunproject.Order.DTO.Request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    String destination;
    List<OrderedFoodRequest> foods;
}
