package com.szincho.kimhyungjunproject.Order.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedFood {
    int id;
    String name;
    long price;
    String description;
}
