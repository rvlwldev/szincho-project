package com.szincho.kimhyungjunproject.Order.DTO.Mapper;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderFoodResponse;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import com.szincho.kimhyungjunproject._Global.Interface.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper(componentModel = "spring", uses = DataMapper.class)
public interface OrderResponseMapper extends DataMapper<OrderResponse, Order> {

    OrderResponseMapper MAPPER = Mappers.getMapper(OrderResponseMapper.class);

    default OrderResponse toDto(Order order, HashMap<Integer, Integer> countsWithFood) {
        OrderResponse response = OrderFoodResponse(order, countsWithFood);

        int totalPrice = 0;
        for (OrderFoodResponse food : response.getFoods()) {
            totalPrice += food.getPrice() * food.getCount();
        }

        response.setTotalPrice(totalPrice);
        response.setDestination(order.getDestination());

        return response;
    }
    default OrderResponse OrderFoodResponse(Order order, HashMap<Integer, Integer> countsWithFood) {
        OrderResponse response = new OrderResponse();

        for (Food food : order.getFoods()) {
            response.addOrderFoodResponse(OrderFoodResponse.builder()
                    .name(food.getName())
                    .price(food.getPrice())
                    .count(countsWithFood.get(food.getId()))
                    .build());
        }

        return response;
    }


}
