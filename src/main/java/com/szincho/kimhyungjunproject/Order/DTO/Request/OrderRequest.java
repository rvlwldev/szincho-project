package com.szincho.kimhyungjunproject.Order.DTO.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(description = "주문 요청 시, 요청 DTO")
public class OrderRequest {

    @ApiModelProperty(value = "주문 목적지", required = true)
    @NotNull(message = "배달주소를 입력해주세요.")
    String destination;

    @ApiModelProperty(value = "주문 음식 메뉴 목록", required = true)
    @NotEmpty(message = "주문 음식을 골라주세요.")
    @Valid
    List<OrderedFoodsRequest> foods;
}
