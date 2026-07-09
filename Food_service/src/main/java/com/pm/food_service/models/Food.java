package com.pm.food_service.models;


import com.pm.food_service.models.FoodType.FoodType;
import com.pm.food_service.models.foodStatus.FoodStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID donor_id;

    @NotBlank
    @Column(nullable = false)
    private String title;


    @NotBlank
    @Column(columnDefinition = "TEXT",nullable = false)
    private String  description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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
    @Column(columnDefinition = "TEXT",nullable = false)
    private String pickup_address;

    @DecimalMin("0.1")
    private double latitude;

    @DecimalMin("0.1")
    private double longitude;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodStatus food_status;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<FoodImages> foodImages = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_At;

    @UpdateTimestamp
    private LocalDateTime updated_At;


}
