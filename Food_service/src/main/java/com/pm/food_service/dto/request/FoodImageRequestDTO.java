package com.pm.food_service.dto.request;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FoodImageRequestDTO {

    @NotNull
    private UUID id;

    @NotNull
    private UUID food_id;

    @NotBlank
    private String title;

    @NotNull
    private FoodType foodType;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotBlank
    private String thumbnailImage;
}
