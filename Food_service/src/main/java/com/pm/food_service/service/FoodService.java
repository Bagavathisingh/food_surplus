package com.pm.food_service.service;

import com.pm.food_service.dto.request.CreateFoodRequestDTO;
import com.pm.food_service.dto.request.FoodImageRequestDTO;
import com.pm.food_service.dto.response.FoodImageResponseDTO;
import com.pm.food_service.dto.response.FoodResponseDTO;
import com.pm.food_service.mapper.Mapper;
import com.pm.food_service.models.Food;
import com.pm.food_service.models.FoodImages;
import com.pm.food_service.repository.FoodRepository;
import com.pm.food_service.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final Mapper mapper;

    public FoodService(FoodRepository foodRepository, ImageRepository imageRepository, Mapper mapper) {
        this.foodRepository = foodRepository;
        this.mapper = mapper;
    }

    public FoodResponseDTO CreateFood(UUID Donor_id, CreateFoodRequestDTO createFoodRequestDTO) {

        Food food = mapper.toFoodEntity(Donor_id,createFoodRequestDTO);
        List<FoodImages> images = createFoodRequestDTO.getImageUrls().stream()
                .map(url->{FoodImages image = new FoodImages();
                image.setImage_url(url);
                image.setFood(food);
                return image;
                }).toList();

        food.setFoodImages(images);
        foodRepository.save(food);
        return mapper.toFoodResponseDTO(food);
    }


    public List<FoodResponseDTO> GetFoodDetails(){
        return foodRepository.findAll().stream().map(mapper::toFoodResponseDTO).toList();
    }
}
