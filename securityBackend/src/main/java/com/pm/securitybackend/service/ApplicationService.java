package com.pm.securitybackend.service;

import com.pm.securitybackend.dto.RequestDTO;
import com.pm.securitybackend.dto.ResponseDTO;
import com.pm.securitybackend.exception.EmailAlreadyExistsException;
import com.pm.securitybackend.mapper.Mapper;
import com.pm.securitybackend.model.AppUser;
import com.pm.securitybackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    public ApplicationService(UserRepository userRepository , Mapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    public ResponseDTO createUser(RequestDTO requestDTO){
        if(userRepository.existsByEmail(requestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email was Already exists !!");
        }
        AppUser newUser = userRepository.save(mapper.toAppUser(requestDTO));
        return mapper.toDto(newUser);
    }
}
