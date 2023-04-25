package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    FoodRepository repo;

    @Autowired
    public FoodService(FoodRepository repo) {
        this.repo = repo;
    }

    public List<Food> getAllFoods() {
        return repo.findAll();
    }

    public Optional<Food> getFood(int id) {
        return repo.findById(id);
    }

    public Food saveFood(Food food) {
        return repo.save(food);
    }

    @Transactional
    public Optional<Food> updateFood(int id, Food target) {
        return getFood(id).map(origin -> origin.updateFields(target));
    }

    @Transactional
    public boolean deleteFood(int id) {
        Optional<Food> food = repo.findById(id);
        if (food.isEmpty()) return false;

        repo.deleteById(id);
        return true;
    }

}
