package com.szincho.kimhyungjunproject.Order.Entity;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String destination;

    @ManyToMany
    @JoinColumn(name = "food_id")
    List<Food> foods;

    boolean isArrived;
    boolean isDeparted;
    boolean isCanceled;
}
