package com.pm.food_service.service.Helper;

import com.pm.food_service.models.foodStatus.FoodStatus;
import org.springframework.stereotype.Component;

@Component
public class HelperFunctions {
    public void validateTheFoodStatus(FoodStatus currentStatus,FoodStatus nextStatus){

        switch (currentStatus){

            case EXPIRED,COMPLETED,CANCELLED -> {
                throw new IllegalArgumentException("The status is not able to change when it is already "+currentStatus);
            }

            case AVAILABLE -> {
                if (nextStatus != FoodStatus.RESERVED &&
                        nextStatus != FoodStatus.CANCELLED &&
                        nextStatus != FoodStatus.EXPIRED) {
                    throw new IllegalArgumentException("Invalid status transition");
                }
            }

            case RESERVED -> {
                if (nextStatus != FoodStatus.AVAILABLE) {
                    throw new IllegalArgumentException("Invalid status transition");
                }
            }
        }
    }
}