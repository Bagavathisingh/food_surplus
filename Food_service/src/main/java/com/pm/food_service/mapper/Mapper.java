package com.pm.food_service.mapper;

import com.pm.food_service.dto.request.CreateFoodRequestDTO;
import com.pm.food_service.dto.request.FoodImageRequestDTO;
import com.pm.food_service.dto.response.FoodImageResponseDTO;
import com.pm.food_service.dto.response.FoodResponseDTO;
import com.pm.food_service.models.Food;
import com.pm.food_service.models.FoodImages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class Mapper {
    public Food toFoodEntity(UUID Donor_id,CreateFoodRequestDTO createFoodRequestDTO){
        Food foodEntity = new Food();
        foodEntity.setDonor_id(Donor_id);
        foodEntity.setTitle(createFoodRequestDTO.getTitle());
        foodEntity.setDescription(createFoodRequestDTO.getDescription());
        foodEntity.setFood_type(createFoodRequestDTO.getFoodType());
        foodEntity.setQuantity(createFoodRequestDTO.getQuantity());
        foodEntity.setQuantity_unit(createFoodRequestDTO.getQuantityUnit());
        foodEntity.setCooked_at(createFoodRequestDTO.getCookedAt());
        foodEntity.setExpiry_time(createFoodRequestDTO.getExpiryTime());
        foodEntity.setPickup_address(createFoodRequestDTO.getPickupAddress());
        foodEntity.setLatitude(createFoodRequestDTO.getLatitude());
        foodEntity.setLongitude(createFoodRequestDTO.getLongitude());
        foodEntity.setFood_status(createFoodRequestDTO.getFoodStatus());
        foodEntity.setCreated_At(createFoodRequestDTO.getCreated_At());
        foodEntity.setUpdated_At(createFoodRequestDTO.getUpdated_At());

        return foodEntity;
    }

    public FoodResponseDTO toFoodResponseDTO(Food food){
        FoodResponseDTO ResponseDto = new FoodResponseDTO();
        ResponseDto.setId(food.getId());
        ResponseDto.setDonorId(food.getDonor_id());
        ResponseDto.setTitle(food.getTitle());
        ResponseDto.setDescription(food.getDescription());
        ResponseDto.setFoodType(food.getFood_type());
        ResponseDto.setQuantity(food.getQuantity());
        ResponseDto.setQuantityUnit(food.getQuantity_unit());
        ResponseDto.setCookedAt(food.getCooked_at());
        ResponseDto.setExpiryTime(food.getExpiry_time());
        ResponseDto.setPickupAddress(food.getPickup_address());
        ResponseDto.setLatitude(food.getLatitude());
        ResponseDto.setLongitude(food.getLongitude());
        ResponseDto.setImages(food.getFoodImages().stream().map(
                images->{
                    FoodImageResponseDTO image = new FoodImageResponseDTO();
                    image.setId(images.getId());
                    image.setImageUrl(images.getImage_url());
                    return image;
                }).toList());
        ResponseDto.setStatus(food.getFood_status());
        ResponseDto.setCreatedAt(food.getCreated_At());
        ResponseDto.setUpdatedAt(food.getUpdated_At());

        return ResponseDto;
    }

//    public FoodImageResponseDTO toFoodImageResponse(FoodImages foodImage){
//        FoodImageResponseDTO foodImageResponseDTO = new FoodImageResponseDTO();
//        foodImageResponseDTO.setId(foodImage.getId());
//        foodImageResponseDTO.setImageUrl(foodImage.getImage_url());
//
//        return foodImageResponseDTO;
//    }
//
//    public FoodImages toFoodImageEntity(FoodImageRequestDTO foodImagesRequest){
//        FoodImages foodImages = new FoodImages();
//        foodImages.setFood_id(foodImagesRequest.getFood_id());
//        foodImages.setImage_url(foodImagesRequest.getThumbnailImage());
//        foodImages.setCreatedAt(foodImages.getCreatedAt());
//
//        return foodImages;
//    }
}
