package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepository repo;

    public List<Food> getAllFoods() {
        return repo.findAll();
    }

}
