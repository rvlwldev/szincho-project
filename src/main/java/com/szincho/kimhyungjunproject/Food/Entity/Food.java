package com.szincho.kimhyungjunproject.Food.Entity;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull(message = "음식이름은 필수값 입니다.")
    String name;

    @NotNull(message = "가격은 필수값 입니다.")
    long price;

    String description;

    public Food (FoodDTO dto){
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.description = dto.getDescription();
    }
}
