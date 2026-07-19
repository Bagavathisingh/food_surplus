package com.pm.securitybackend.service;


import com.pm.securitybackend.dto.*;
import com.pm.securitybackend.exception.EmailAlreadyExistsException;
import com.pm.securitybackend.exception.UserNotFoundedException;
import com.pm.securitybackend.mapper.Mapper;
import com.pm.securitybackend.model.AppUser;
import com.pm.securitybackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ApplicationService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder encoder;


    public ApplicationService(UserRepository userRepository , Mapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }



    public ResponseDTO createUser(RequestDTO requestDTO){
        String mail = requestDTO.getEmail();
        if(userRepository.existsByEmail(mail)){
            log.warn("{} The email was already exists {}",OffsetDateTime.now(),mail);
            throw new EmailAlreadyExistsException("Email was Already exists !!");
        }
        AppUser newUser = userRepository.save(mapper.toAppUser(requestDTO));
        log.info("\n {} The account was successfully created for Id  {}", OffsetDateTime.now(),newUser.getId());
        return mapper.toDto(newUser);
    }


    public ResponseEntity<?> loginUser(LoginRequestDto requestDTO){
        String mail = requestDTO.getEmail();
        String pass =  requestDTO.getPassword();
        var user = userRepository.findByEmail(mail);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","There is no account with this email "+mail));
        }

        AppUser userDetail = user.get();
        if(!encoder.matches(pass,userDetail.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","The username or password was Invalid !"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(userDetail));
    }


    public List<GetUsersDto> getUsers() {
        return userRepository.findAll().stream().map(mapper::ToDtoOfAll).toList();
    }



    public UpdateUserResponseDto UpdateUserDetails(UUID id, RequestDTO requestDTO) {
        AppUser user = userRepository.findById(id).orElseThrow(()->new UserNotFoundedException("The User is not founded "));
        if(userRepository.existsByEmailAndIdNot(requestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("The user with this Email is Already exists ");
        }

        user.setId(id);
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPhone(requestDTO.getPhone());
        user.setUpdateAt(requestDTO.getUpdateAt());

        return mapper.UpdatedDTO(user);
    }


    public Map<String,String> DeleteUserDetail(UUID id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return Map.of("message","The user detail is Deleted Successfully ");
        }
        else{
            return Map.of("message","the user detail is not founded ");
        }
    }


    public GetUsersDto getuserById(UUID id) {
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundedException("The user is not founded ");
        }
        return mapper.ToDtoOfAll(user.get());
    }
}
