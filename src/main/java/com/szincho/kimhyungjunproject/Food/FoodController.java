package com.szincho.kimhyungjunproject.Food;

import com.szincho.kimhyungjunproject.Food.DTO.FoodDTO;
import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Food", description = "음식 정보 관련 API")
@Api(tags = "Food")
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

    @Operation(summary = "전체 음식 정보 조회", tags = "Food")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회성공")
    })
    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return service.getAllFoods();
    }

    @Operation(summary = "선택된 음식 정보 조회", tags = "Food")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 음식 ID 조회 시")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFood(@PathVariable("id") int id) throws FoodNotFoundException {
        return ResponseEntity.ok().body(service.getFood(id));
    }

    @Operation(summary = "음식 등록", tags = "Food")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록성공"),
            @ApiResponse(responseCode = "400", description = "FoodDTO의 필수 값/조건이 누락되었을 경우")
    })
    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid FoodDTO dto) {
        dto = service.saveFood(mapper.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "음식 등록", tags = "Food")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정성공"),
            @ApiResponse(responseCode = "400", description = "FoodDTO의 필수 값/조건이 누락되었을 경우, 수정 시 모든 값이 입력되어야함")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable("id") int id, @RequestBody @Valid FoodDTO dto) throws FoodNotFoundException {
        FoodDTO updated = service.updateFood(id, mapper.toEntity(dto));
        return ResponseEntity.ok().body(updated);
    }

    @Operation(summary = "음식 정보 삭제", tags = "Food")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제성공"),
            @ApiResponse(responseCode = "404", description = "삭제 대상 음식의 ID이 존재하지 않을 경우")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") int id) throws FoodNotFoundException {
        service.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

}
