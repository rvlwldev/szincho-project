package com.szincho.kimhyungjunproject.Food.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "음식 정보 DTO")
public class FoodDTO {

    @ApiModelProperty(value = "음식 이름", required = true)
    @NotNull(message = "음식이름을 입력하세요")
    String name;

    @ApiModelProperty(
            value = "음식의 가격, 등록 시, 1원 단위의 금액은 자동 '절삭' 됩니다. 최소금액은 1000원 입니다.",
            required = true)
    @Min(value = 1000, message = "최소금액은 1000원 이상입니다.")
    long price;

    @ApiModelProperty(
            value = "음식의 관한 자세한 설명",
            example = "직접 키우고 튀긴 치킨입니다")
    String description;

    public void setPrice(long price) {
        this.price = price - price % 10;
    }
}
