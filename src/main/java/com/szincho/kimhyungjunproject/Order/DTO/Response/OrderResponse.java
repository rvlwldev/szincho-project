package com.szincho.kimhyungjunproject.Order.DTO.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "주문 요청 결과의 대한 DTO")
public class OrderResponse {

    @ApiModelProperty(value = "목적지")
    String destination;

    @ApiModelProperty(value = "주문한 음식들 목록, 각각의 수량과 총 가격")
    List<OrderFoodResponse> foods = new ArrayList<>();

    @ApiModelProperty(value = "주문한 음식들의 가격의 총합")
    long totalPrice;

    public void addOrderFoodResponse(OrderFoodResponse orderedFood) {
        this.foods.add(orderedFood);
    }
}