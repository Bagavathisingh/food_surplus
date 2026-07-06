package com.pm.food_service.dto.response;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FoodResponseDTO {

    private UUID id;

    private UUID donorId;

    private String title;

    private String description;

    private FoodType foodType;

    private Integer quantity;

    private String quantityUnit;

    private FoodStatus status;

    private LocalDateTime cookedAt;

    private LocalDateTime expiryTime;

    private String pickupAddress;

    private Double latitude;

    private Double longitude;

    private List<FoodImageResponseDTO> images;

    private LocalDateTime createdAt;
}
