package com.szincho.kimhyungjunproject.Food.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    String name;
    long price;
    String description;
}
