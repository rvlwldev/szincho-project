package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject._Global.Interface.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = DataMapper.class)
public interface FoodMapper extends DataMapper<FoodDTO, Food> {
    FoodMapper MAPPER = Mappers.getMapper(FoodMapper.class);

    FoodDTO toDto(final Food entity);

    Food toEntity(final FoodDTO dto);
}
