package com.pm.securitybackend.controller;

import com.pm.securitybackend.dto.*;
import com.pm.securitybackend.dto.validator.CreateUserValidationGroup;
import com.pm.securitybackend.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class ApplicationController {
    @Autowired
    private ApplicationService service;
    @PostMapping("/userLogin")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginRequestDto requestDTO){
        return service.loginUser(requestDTO);
    }

    @PostMapping("/userRegister")
    public ResponseEntity<ResponseDTO> signUp(@Validated({Builder.Default.class, CreateUserValidationGroup.class}) @RequestBody RequestDTO requestDTO){
        ResponseDTO response = service.createUser(requestDTO);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetUsers")
    public ResponseEntity<List<GetAllUsersDto>> GetUsers(){
        List<GetAllUsersDto> users = service.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/userUpdate/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable UUID id, @Validated({Builder.Default.class}) @RequestBody RequestDTO requestDTO){
        UpdateUserResponseDto responseDTO = service.UpdateUserDetails(id,requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<Map<String,String>> DeleteUser(@PathVariable UUID id){
        Map<String,String> response = service.DeleteUserDetail(id);
        return ResponseEntity.ok().body(response);
    }


}
