package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Entity.Food;
import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    public List<Food> getAllByIds(List<Integer> ids) {
        return repo.findAllById(ids);
    }

    public FoodDTO getFood(int id) throws FoodNotFoundException {
        validateFoodExists(id);
        return mapper.toDto(repo.findById(id).get());
    }

    public FoodDTO saveFood(Food food) {
        return mapper.toDto(repo.save(food));
    }

    @Transactional
    public FoodDTO updateFood(int id, Food target) throws FoodNotFoundException {
        validateFoodExists(id);

        target = new Food(id, target.getName(), target.getPrice(), target.getDescription());

        return saveFood(target);
    }

    @Transactional
    public void deleteFood(int id) throws FoodNotFoundException {
        validateFoodExists(id);

        repo.deleteById(id);
    }

    private void validateFoodExists(int id) throws FoodNotFoundException {
        if (!repo.existsById(id)) throw new FoodNotFoundException(id);
    }

}
