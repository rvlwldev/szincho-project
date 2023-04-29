package com.szincho.kimhyungjunproject.Order.DTO.Mapper;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderFoodResponse;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import com.szincho.kimhyungjunproject._Global.Interface.DataMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = DataMapper.class)
public interface OrderResponseMapper extends DataMapper<OrderResponse, Order> {

    OrderResponseMapper MAPPER = Mappers.getMapper(OrderResponseMapper.class);

    @Mapping(source = "foods", target = "foods", qualifiedByName = "toOrderResponseList")
    OrderResponse toDto(final Order entity);

    @Named("toOrderResponseList")
    default List<OrderFoodResponse> toOrderResponseList(List<Food> foods) {
        HashMap<Integer, Integer> countsWithFood = getCountWithFoodMap(foods);

        return Set.copyOf(foods).stream()
                .map(food -> toOrderResponse(food, countsWithFood.get(food.getId())))
                .collect(Collectors.toList());
    }

    @AfterMapping
    default void afterMapping(@MappingTarget OrderResponse dto) {
        int totalPrice = 0;
        for (OrderFoodResponse food : dto.getFoods()) totalPrice += food.getPrice() * food.getCount();

        dto.setTotalPrice(totalPrice);
    }

    default OrderFoodResponse toOrderResponse(Food food, int count) {
        return OrderFoodResponse.builder()
                .name(food.getName())
                .price(food.getPrice())
                .count(count)
                .build();
    }

    default HashMap<Integer, Integer> getCountWithFoodMap(List<Food> foods) {
        HashMap<Integer, Integer> countsWithFood = new HashMap<>();

        for (Food orderedFood : foods) {
            int count = countsWithFood.getOrDefault(orderedFood.getId(), 0);

            countsWithFood.put(orderedFood.getId(), count + 1);
        }
        return countsWithFood;
    }

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
