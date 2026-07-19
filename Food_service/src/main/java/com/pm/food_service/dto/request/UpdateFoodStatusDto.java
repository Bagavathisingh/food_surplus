package com.pm.food_service.dto.request;

import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFoodStatusDto {
    @NotNull
    private FoodStatus status;

}
