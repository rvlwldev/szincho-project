package com.szincho.kimhyungjunproject.Order.DTO.Request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class OrderedFoodsRequest {
    @Min(value = 1, message = "음식을 선택해주세요.")
    int id;

    @Min(value = 1, message = "1개 이상 주문이 가능합니다.")
    int count;
}
