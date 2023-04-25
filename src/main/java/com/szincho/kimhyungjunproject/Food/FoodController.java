package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable("id") int id) {
        Optional<Food> food = service.getFood(id);

        return food.map(result -> ResponseEntity.ok().body(result))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodDTO dto) {
        Food food = new Food(dto);
        food = service.saveFood(food);

        return ResponseEntity.status(HttpStatus.CREATED).body(food);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") int id, @RequestBody FoodDTO dto) {
        Food food = new Food(dto);

        return service.updateFood(id, food)
                .map(result -> ResponseEntity.ok().body(result))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") int id) {
        if (service.deleteFood(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

}
