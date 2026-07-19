package com.pm.food_service.controller;

import com.pm.food_service.dto.request.CreateFoodRequestDTO;
import com.pm.food_service.dto.request.FoodImageRequestDTO;
import com.pm.food_service.dto.request.UpdateFoodRequestDTO;
import com.pm.food_service.dto.request.UpdateFoodStatusDto;
import com.pm.food_service.dto.response.FoodImageResponseDTO;
import com.pm.food_service.dto.response.FoodResponseDTO;
import com.pm.food_service.models.Food;
import com.pm.food_service.models.foodStatus.FoodStatus;
import com.pm.food_service.repository.FoodRepository;
import com.pm.food_service.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService service;
    private final FoodRepository foodRepository;

    public FoodController(FoodService service, FoodRepository foodRepository){
        this.service = service;
        this.foodRepository = foodRepository;
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

    @GetMapping("/getFood/{id}")
    public ResponseEntity<FoodResponseDTO> getFoodDetails(@PathVariable UUID id){
        FoodResponseDTO responseDTO = service.GetFoodDetail(id);
        return ResponseEntity.ok().body(responseDTO);
    }


    @DeleteMapping("/deleteFood/{id}")
    public ResponseEntity<Map<String,String>> deleteFood(@PathVariable UUID id){
        if(!service.DeleteFoodDetails(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","The Item is not present or already deleted !"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","The Food item was deleted successfully !"));
    }

    @PatchMapping("/UpdateFood/{id}")
    public ResponseEntity<?> updateFood(@PathVariable UUID id , @RequestBody Map<Object,Object> updateFoodRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.updateFood(id,updateFoodRequestDTO)
        );
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Map<String,String>> deleteAll(){
        foodRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of("message","All the data are deleted ")
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FoodResponseDTO> setStatus(@PathVariable UUID id, @RequestBody UpdateFoodStatusDto foodStatus){
        return ResponseEntity.ok().body(service.setStatus(id,foodStatus.getStatus()));
    }
}
