package com.pm.food_service.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FoodImageResponseDTO {

        @NotNull
        private UUID id;

        @NotBlank
        private String imageUrl;
}
