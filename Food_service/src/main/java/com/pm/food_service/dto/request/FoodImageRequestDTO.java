package com.pm.food_service.dto.request;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class FoodImageRequestDTO {

    private UUID id;

    private String title;

    private FoodType foodType;

    private Integer quantity;

    private FoodStatus status;

    private LocalDateTime expiryTime;

    private String pickupAddress;

    private String thumbnailImage;
}
