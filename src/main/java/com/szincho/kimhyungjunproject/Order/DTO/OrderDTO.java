package com.szincho.kimhyungjunproject.Order.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    String destination;
    List<Integer> foods;
}
