package com.pm.food_service.dto.request;

import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pm.food_service.models.FoodType.FoodType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFoodRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private FoodType foodType;

    @NotNull
    @Min(1)
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

    @NotNull
    private FoodStatus foodStatus;

    @NotNull
    private List<String> imageUrls = new ArrayList<>();
}
