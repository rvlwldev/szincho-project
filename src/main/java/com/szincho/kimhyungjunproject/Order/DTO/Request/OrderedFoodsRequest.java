package com.szincho.kimhyungjunproject.Order.DTO.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel(description = "주문 요청 시, 주문한 음식의 대한 DTO")
public class OrderedFoodsRequest {

    @ApiModelProperty(value = "주문 음식의 ID", required = true)
    @Min(value = 1, message = "음식을 선택해주세요.")
    int id;

    @ApiModelProperty(value = "주문 음식의 수량 (1개 이상)", required = true)
    @Min(value = 1, message = "1개 이상 주문이 가능합니다.")
    int count;
}
