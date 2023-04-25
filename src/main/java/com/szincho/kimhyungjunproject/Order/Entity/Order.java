package com.szincho.kimhyungjunproject.Order.Entity;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class Order {
    long id;
    List<Food> foods;
    int totalPrice;
    Date orderTime;
    boolean isArrived;
    String destination;
}
