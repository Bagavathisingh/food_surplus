package com.pm.securitybackend.mapper;

import com.pm.securitybackend.dto.RequestDTO;
import com.pm.securitybackend.dto.ResponseDTO;
import com.pm.securitybackend.dto.UserResponseDTO;
import com.pm.securitybackend.model.AppUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Mapper {
    public ResponseDTO toDto(AppUser user){
        UserResponseDTO UserresponseDTO = new UserResponseDTO();
        UserresponseDTO.setId(user.getId());
        UserresponseDTO.setName(user.getName());
        UserresponseDTO.setEmail(user.getEmail());
        UserresponseDTO.setCreatedAt(String.valueOf(user.getCreatedAt()));

        return new ResponseDTO(
                "12324123412313@#@#@@#&^&@$@#%@#%@^@#",
                "Bearer ",
                "You have been loggedIn",
                3400,UserresponseDTO
        );
    }

    public AppUser toAppUser(RequestDTO requestDTO){
        AppUser newUser = new AppUser();
        newUser.setName(requestDTO.getName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setPassword(requestDTO.getPassword());
        newUser.setCreatedAt(LocalDate.parse(requestDTO.getCreatedAt()));
        return newUser;
    }
}
