package com.szincho.kimhyungjunproject.Order.Entity;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String destination;
    List<Food> foods;
    long totalPrice;
    Date orderTime;
    boolean isDeparted;
    boolean isArrived;
}
