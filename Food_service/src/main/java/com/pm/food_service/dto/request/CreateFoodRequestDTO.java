package com.pm.food_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import com.pm.food_service.models.FoodType.FoodType;
public class CreateFoodRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private FoodType foodType;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String quantityUnit;

    @NotNull
    private LocalDateTime cookedAt;

    @NotNull
    private LocalDateTime expiryTime;

    @NotBlank
    private String pickupAddress;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private List<String> imageUrls;

}
