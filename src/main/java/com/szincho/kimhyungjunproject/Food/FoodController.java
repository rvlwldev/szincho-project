package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
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
    private final FoodMapper mapper;

    @Autowired
    public FoodController(FoodService service, FoodMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return service.getAllFoods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFood(@PathVariable("id") int id) throws FoodNotFoundException {
        Optional<FoodDTO> food = service.getFood(id);

        return food.map(result -> ResponseEntity.ok().body(result))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO dto) {
        dto = service.saveFood(mapper.MAPPER.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable("id") int id, @RequestBody FoodDTO dto) throws FoodNotFoundException {
        return service.updateFood(id, mapper.toEntity(dto))
                .map(result -> ResponseEntity.ok().body(result))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") int id) throws FoodNotFoundException {
        service.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

}
