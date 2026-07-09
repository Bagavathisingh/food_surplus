package com.pm.food_service.dto.response;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FoodSummaryResponseDTO {
    @NotNull
    private UUID id;

    @NotBlank
    private String title;

    @NotNull
    private FoodType foodType;

    @NotNull
    private Integer quantity;

    @NotNull
    private FoodStatus status;

    @NotNull
    private LocalDateTime expiryTime;

    @NotBlank
    private String pickupAddress;

    @NotBlank
    private String thumbnailImage;
}
