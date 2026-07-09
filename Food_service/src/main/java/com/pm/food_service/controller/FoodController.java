package com.pm.food_service.controller;

import com.pm.food_service.dto.request.CreateFoodRequestDTO;
import com.pm.food_service.dto.request.FoodImageRequestDTO;
import com.pm.food_service.dto.response.FoodImageResponseDTO;
import com.pm.food_service.dto.response.FoodResponseDTO;
import com.pm.food_service.models.Food;
import com.pm.food_service.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service){
        this.service = service;
    }

    @PostMapping("/createFood")
    public ResponseEntity<FoodResponseDTO> createFood(@RequestHeader("X-User-Id") UUID id,
                                                      @RequestBody CreateFoodRequestDTO createFoodRequestDTO){
        FoodResponseDTO foodResponseDTO = service.CreateFood(id,createFoodRequestDTO);

        return ResponseEntity.ok().body(foodResponseDTO);
    }

    @GetMapping("/getFoodList")
    public ResponseEntity<List<FoodResponseDTO>> GetFoodDetails(){
        List<FoodResponseDTO> response = service.GetFoodDetails();
        return ResponseEntity.ok().body(response);
    }
}
