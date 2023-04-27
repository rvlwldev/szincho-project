package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodRepository repo;
    private final FoodMapper mapper;

    @Autowired
    public FoodService(FoodRepository repo, FoodMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<FoodDTO> getAllFoods() {
        return repo.findAll().stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<FoodDTO> getFood(int id) {
        return repo.findById(id).map(mapper::toDto);
    }

    public FoodDTO saveFood(Food food) {
        return mapper.toDto(repo.save(food));
    }

    @Transactional
    public Optional<FoodDTO> updateFood(int id, Food target) {
        return getFood(id).map(selected -> new FoodDTO(target.getName(), target.getPrice(), target.getDescription()))
                .map(updated -> saveFood(mapper.toEntity(updated)));
    }

    @Transactional
    public boolean deleteFood(int id) {
        Optional<Food> food = repo.findById(id);
        if (food.isEmpty()) return false;

        repo.deleteById(id);
        return true;
    }

}
