package com.szincho.kimhyungjunproject.Food.Entity;

import com.szincho.kimhyungjunproject.Restaurant.Entity.Restaurant;

// @Entity
public class Food {
    Restaurant restaurant;
    String name;
    long price;
    Category category;
    Option option;
    String description;
}
