package com.pm.food_service.dto.request;

import java.time.LocalDateTime;

public class UpdateFoodRequestDTO {

    private String title;

    private String description;

    private Integer quantity;

    private LocalDateTime expiryTime;

    private String pickupAddress;
}
