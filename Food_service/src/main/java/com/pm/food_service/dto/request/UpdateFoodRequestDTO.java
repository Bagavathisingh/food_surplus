package com.pm.food_service.dto.request;

import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdateFoodRequestDTO {
    private String title;

    private String description;

    private FoodType foodType;

    private Integer quantity;

    private String quantityUnit;

    private LocalDateTime expiryTime;

    private String pickupAddress;

    private Double latitude;

    private Double longitude;

    private FoodStatus foodStatus;

    private List<String> imageUrls;
}
