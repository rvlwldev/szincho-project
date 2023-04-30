package com.szincho.kimhyungjunproject.Order.DTO.Request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {
    @NotNull(message = "배달주소를 입력해주세요.")
    String destination;

    @NotEmpty(message = "주문 음식을 골라주세요.")
    @Valid
    List<OrderedFoodsRequest> foods;
}
