package com.pm.food_service.dto.request;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UpdateFoodRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String  description;

    @NotNull
    private FoodType food_type;


    @Min(1)
    private int quantity;


    @NotBlank
    private String quantity_unit;


    @NotNull
    private LocalDateTime cooked_at;

    @NotNull
    private LocalDateTime expiry_time;

    @NotBlank
    private String pickup_address;

    @DecimalMin("0.1")
    private double latitude;

    @DecimalMin("0.1")
    private double longitude;

    @NotNull
    private FoodStatus food_status;

    @NotNull
    private LocalDate Updated_At;

}
