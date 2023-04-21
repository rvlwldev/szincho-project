package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    FoodRepository repo;

    @Autowired
    public FoodService (FoodRepository repo) {
        this.repo = repo;
    }

    public List<Food> getAllFoods() {
        return repo.findAll();
    }

    public Food getFood(int id) {
        return repo.findById(id)
                .orElse(new Food());
    }

    public Food saveFood(Food food) {
        return repo.save(food);
    }

}
