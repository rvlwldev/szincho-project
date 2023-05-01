package com.szincho.kimhyungjunproject.Order.DTO.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "주문 요청한 음식의 DTO")
public class OrderFoodResponse {

    @ApiModelProperty(value = "주문한 음식의 이름")
    String name;

    @ApiModelProperty(value = "주문한 음식의 수량")
    int count;

    @ApiModelProperty(value = "주문한 음식의 가격 (음식의 가격 * 수량)")
    long price;
}
