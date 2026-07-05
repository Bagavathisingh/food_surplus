package com.pm.food_service.repository;

import com.pm.food_service.models.FoodImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<FoodImages, UUID> {

}
