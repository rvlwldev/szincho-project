package com.szincho.kimhyungjunproject.Order.DTO.Mapper;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Food.FoodService;
import com.szincho.kimhyungjunproject.Order.DTO.Request.OrderRequest;
import com.szincho.kimhyungjunproject.Order.DTO.Request.OrderedFoodsRequest;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import com.szincho.kimhyungjunproject._Global.Interface.DataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = DataMapper.class)
public interface OrderRequestMapper extends DataMapper<OrderRequest, Order> {

    OrderRequestMapper MAPPER = Mappers.getMapper(OrderRequestMapper.class);

    Order toEntity(final OrderRequest dto);

    default Order getOrderByRequestedDto(OrderRequest request, FoodService foodService) {
        List<Integer> ids = request.getFoods().stream()
                .map(OrderedFoodsRequest::getId)
                .collect(Collectors.toList());

        List<Food> selectedFood = foodService.getAllByIds(ids);

        return Order.builder()
                .destination(request.getDestination())
                .foods(selectedFood)
                .build();
    }

    default HashMap<Integer, Integer> getCountWithFoodMap(OrderRequest request) {
        HashMap<Integer, Integer> countsWithFood = new HashMap<>();

        for (OrderedFoodsRequest orderedFood : request.getFoods()) {
            int count = countsWithFood.getOrDefault(orderedFood.getId(), 0);
            count += orderedFood.getCount();

            countsWithFood.put(orderedFood.getId(), count);
        }

        return countsWithFood;
    }

}
