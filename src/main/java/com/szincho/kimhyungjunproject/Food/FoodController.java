package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService service;

    @Autowired
    public FoodController(FoodService service) {
        this.service = service;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return service.getAllFoods();
    }

    @GetMapping ("/{id}")
    public Food getFood(@PathVariable("id") int id) {
        return service.getFood(id);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodDTO dto) {
        Food food = new Food(dto);
        food = service.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).body(food);
    }

}
