package com.pm.food_service.dto.response;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class FoodResponseDTO {

    @NotBlank
    private UUID id;

    @NotNull
    private UUID donorId;

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
    private FoodStatus status;

    @NotNull
    private LocalDateTime cookedAt;

    @NotNull
    private LocalDateTime expiryTime;

    @NotBlank
    private String pickupAddress;

    @NotNull
    @DecimalMin("0.1")
    private Double latitude;

    @NotNull
    @DecimalMin("0.1")
    private Double longitude;

    @NotNull
    private List<FoodImageResponseDTO> images;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime UpdatedAt;
}
