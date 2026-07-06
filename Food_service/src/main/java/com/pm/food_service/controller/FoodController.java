package com.pm.food_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @GetMapping
    public ResponseEntity<String> greet(@RequestHeader("X-User-Id")UUID id, @RequestHeader("X-User-Role") String role){
        return ResponseEntity.ok().body(id+" "+role);
    }
}
