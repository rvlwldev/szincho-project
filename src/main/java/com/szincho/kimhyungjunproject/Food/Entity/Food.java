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
    private int id;

    @NotNull(message = "음식이름은 필수값 입니다.")
    private String name;

    @NotNull(message = "가격은 필수값 입니다.")
    private long price;

    private String description;

    public Food(FoodDTO dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.description = dto.getDescription();
    }

    public Food updateFields(Food target) {
        setName(target.getName());
        setPrice(target.getPrice());
        setDescription(target.getDescription());

        return this;
    }

    private void setName(String name) {
        if (name != null) this.name = name;
    }

    private void setPrice(long price) {
        if (price > 0) this.price = price;
    }

    private void setDescription(String description) {
        if (description != null) this.description = description;
    }


}
