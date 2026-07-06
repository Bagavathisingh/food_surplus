package com.pm.securitybackend.mapper;

import com.pm.securitybackend.JwtUtil;
import com.pm.securitybackend.dto.*;
import com.pm.securitybackend.model.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public Mapper(JwtUtil jwtUtil, PasswordEncoder encoder){
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }
    public ResponseDTO toDto(AppUser user){
        UserResponseDTO UserresponseDTO = new UserResponseDTO();
        UserresponseDTO.setId(user.getId());
        UserresponseDTO.setName(user.getName());
        UserresponseDTO.setEmail(user.getEmail());
        UserresponseDTO.setVerified(user.getVerified());
        UserresponseDTO.setRole(user.getRole());
        UserresponseDTO.setCreatedAt(String.valueOf(user.getCreatedAt()));
        UserresponseDTO.setUpdateAt(String.valueOf(user.getUpdateAt()));

        String JwtToken = jwtUtil.tokenGenerate(user.getEmail(),user.getRole().name(),user.getId());
        return new ResponseDTO(
                JwtToken,
                "Bearer ",
                "You have been loggedIn",
                UserresponseDTO
        );
    }

    public UpdateUserResponseDto UpdatedDTO(AppUser user){
        UpdateUserResponseDto updateDto = new UpdateUserResponseDto();
        updateDto.setId(user.getId());
        updateDto.setEmail(user.getEmail());
        updateDto.setName(user.getName());
        updateDto.setUpdateAt(user.getUpdateAt().toString());
        updateDto.setPhone(user.getPhone());
        updateDto.setMessage("Your Details has been Updated !");
        return updateDto;
    }

    public GetAllUsersDto ToDtoOfAll(AppUser user){
        GetAllUsersDto users = new GetAllUsersDto();
        users.setId(user.getId());
        users.setName(user.getName());
        users.setEmail(user.getEmail());
        users.setCreatedAt(user.getCreatedAt());
        users.setUpdateAt(user.getUpdateAt());
        return users;
    }
    public AppUser toAppUser(RequestDTO requestDTO){
        AppUser newUser = new AppUser();
        newUser.setName(requestDTO.getName());
        newUser.setEmail(requestDTO.getEmail());


        String pass = encoder.encode(requestDTO.getPassword());
        newUser.setPassword(pass);
        newUser.setPhone(requestDTO.getPhone());
        newUser.setRole(requestDTO.getRole());
        newUser.setVerified(requestDTO.getVerified());
        newUser.setCreatedAt(requestDTO.getCreatedAt());
        newUser.setUpdateAt(requestDTO.getUpdateAt());
        return newUser;
    }
}
