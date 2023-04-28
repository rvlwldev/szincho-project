package com.szincho.kimhyungjunproject.Order.DTO.Request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedFoodRequest {
    String id;
    int count;
}
