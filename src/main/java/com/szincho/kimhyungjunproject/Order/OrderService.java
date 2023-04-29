package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import com.szincho.kimhyungjunproject.Food.FoodRepository;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.Enum.OrderStatus;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.IllegalOrderException;
import com.szincho.kimhyungjunproject.Order.DTO.Exception.OrderNotFoundException;
import com.szincho.kimhyungjunproject.Order.DTO.Mapper.OrderResponseMapper;
import com.szincho.kimhyungjunproject.Order.DTO.Response.OrderResponse;
import com.szincho.kimhyungjunproject.Order.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final FoodRepository foodRepo;
    private final OrderResponseMapper mapper;

    @Autowired
    public OrderService(OrderRepository orderRepo, FoodRepository foodRepo, OrderResponseMapper mapper) {
        this.orderRepo = orderRepo;
        this.foodRepo = foodRepo;
        this.mapper = mapper;
    }

    @Transactional
    public OrderResponse saveOrder(Order order, HashMap<Integer, Integer> CountWithFoodMap) throws FoodNotFoundException {
        validateOrderFoods(order, CountWithFoodMap);
        return mapper.toDtoByCountWithFoodMap(orderRepo.save(order), CountWithFoodMap);
    }

    private void validateOrderFoods(Order order, HashMap<Integer, Integer> CountWithFoodMap) throws FoodNotFoundException {
        List<Food> selectedFoods = foodRepo.findAllById(order.getFoods().stream()
                .map(Food::getId)
                .collect(Collectors.toList()));

        Set<Integer> orderedFoodIds = CountWithFoodMap.keySet();

        if (selectedFoods.size() != orderedFoodIds.size()) throw new FoodNotFoundException();
    }

    public List<OrderResponse> getAllOrderHistoryDESC() {
        Sort sort = Sort.by("orderTime").descending();

        return orderRepo.findAll(sort).stream()
                .filter(order -> !order.isCanceled())
                .filter(Order::isArrived)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelOrder(long id) throws IllegalOrderException, OrderNotFoundException {
        validateOrderId(id);
        orderRepo.cancelOrder(id);
    }

    @Transactional
    public void departOrder(long id) throws IllegalOrderException, OrderNotFoundException {
        validateOrderId(id);
        orderRepo.departOrder(id);
    }

    @Transactional
    public void arriveOrder(long id) throws IllegalOrderException, OrderNotFoundException {
        validateOrderId(id);

        if (!orderRepo.findById(id).get().isDeparted()) {
            throw new IllegalOrderException(OrderStatus.NOT_ALLOWED_TO_BE_ARRIVED.toString());
        }

        orderRepo.arriveOrder(id);
    }

    private void validateOrderId(long id) {
        Optional<Order> order = orderRepo.findById(id);

        if (order.isPresent()) {
            if (order.get().isCanceled()) throw new IllegalOrderException(OrderStatus.CANCELED.toString());
            if (order.get().isDeparted()) throw new IllegalOrderException(OrderStatus.DEPARTED.toString());
            if (order.get().isArrived()) throw new IllegalOrderException(OrderStatus.ARRIVED.toString());
        } else throw new OrderNotFoundException();
    }

}
