package com.pm.securitybackend.controller;

import com.pm.securitybackend.dto.RequestDTO;
import com.pm.securitybackend.dto.ResponseDTO;
import com.pm.securitybackend.model.AppUser;
import com.pm.securitybackend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/receiver")
public class ApplicationController {
    @Autowired
    private ApplicationService service;

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody RequestDTO requestDTO){
        ResponseDTO response = service.createUser(requestDTO);
        return ResponseEntity.ok().body(response);
    }
}
