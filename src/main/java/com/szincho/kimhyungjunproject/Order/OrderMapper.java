package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Order.DTO.OrderDTO;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import com.szincho.kimhyungjunproject._Global.Interface.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = DataMapper.class)
public interface OrderMapper extends DataMapper<OrderDTO, Order>{
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(final Order entity);

    Order toEntity(final OrderDTO dto);
}
