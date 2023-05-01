package com.szincho.kimhyungjunproject.Food.DTO;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    @NotNull(message = "음식이름을 입력하세요")
    String name;

    @Min(value = 1000, message = "최소금액은 1000원 이상입니다.")
    long price;

    String description;

    public void setPrice(long price) {
        this.price = price - price % 10;
    }
}
