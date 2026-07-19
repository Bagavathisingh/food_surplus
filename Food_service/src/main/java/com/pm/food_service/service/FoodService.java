package com.pm.food_service.service;

import com.pm.food_service.dto.request.CreateFoodRequestDTO;
import com.pm.food_service.dto.response.FoodResponseDTO;
import com.pm.food_service.mapper.Mapper;
import com.pm.food_service.models.Food;
import com.pm.food_service.models.FoodImages;
import com.pm.food_service.models.foodStatus.FoodStatus;
import com.pm.food_service.repository.FoodRepository;
import com.pm.food_service.service.Helper.HelperFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodService {
    private static final Logger log = LoggerFactory.getLogger(FoodService.class);
    private final FoodRepository foodRepository;
    private final Mapper mapper;
    private final HelperFunctions helperFunctions;
    public FoodService(FoodRepository foodRepository, Mapper mapper, HelperFunctions helperFunctions) {
        this.foodRepository = foodRepository;
        this.mapper = mapper;
        this.helperFunctions = helperFunctions;
    }

    public FoodResponseDTO CreateFood(UUID Donor_id, CreateFoodRequestDTO createFoodRequestDTO) {

        Food food = mapper.toFoodEntity(Donor_id, createFoodRequestDTO);
        List<FoodImages> images = createFoodRequestDTO.getImageUrls().stream()
                .map(url -> {
                    FoodImages image = new FoodImages();
                    image.setImage_url(url);
                    image.setFood(food);
                    return image;
                }).toList();

        food.setFoodImages(images);
        foodRepository.save(food);
        return mapper.toFoodResponseDTO(food);
    }


    public List<FoodResponseDTO> GetFoodDetails() {
        return foodRepository.findAll().stream().map(mapper::toFoodResponseDTO).toList();
    }

    public boolean DeleteFoodDetails(UUID id) {
        if (foodRepository.existsById(id)) {
            try {
                foodRepository.deleteById(id);
                return true;
            } catch (Exception ex) {
                log.error("The error was happen , application try to delete the food detail from the Database  {}", ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public Map<?,?> updateFood(UUID id , Map<Object,Object> fields){
        Optional<Food> food = foodRepository.findById(id);

        if(food.isEmpty()){
            return Map.of("message","The detail is not avail ! , so you can't change any values ");
        }

        fields.forEach(
                (key,values)->{
                    Field field = ReflectionUtils.findField(Food.class,(String) key);
                    if(field != null){
                        field.setAccessible(true);
                        ReflectionUtils.setField(field,food.get(),values);
                    }
                }
        );

        Food updatedFood = foodRepository.save(food.get());

        return Map.of("updatedDetails",mapper.toFoodResponseDTO(updatedFood));
    }

    public FoodResponseDTO GetFoodDetail(UUID id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(mapper::toFoodResponseDTO).orElse(null);
    }

    public FoodResponseDTO setStatus(UUID id, FoodStatus NewfoodStatus) {
        Food food = foodRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("The user is not exist !"));
        helperFunctions.validateTheFoodStatus(food.getFood_status(),NewfoodStatus);
        food.setFood_status(NewfoodStatus);

        Food updatedFood = foodRepository.save(food);

        return mapper.toFoodResponseDTO(updatedFood);
    }

}
