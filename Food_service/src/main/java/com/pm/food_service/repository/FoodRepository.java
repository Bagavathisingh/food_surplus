package com.pm.food_service.repository;

import com.pm.food_service.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
}
