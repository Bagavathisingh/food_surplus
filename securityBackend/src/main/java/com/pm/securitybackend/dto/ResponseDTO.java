package com.pm.securitybackend.dto;

import com.pm.securitybackend.model.AppUser;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    @NotNull
    private final String JWTToken;

    @NotNull
    private final String TokenType;

    @NotNull
    private final String message;

    @NotNull
    private final long ExpiredDate;

    @NotNull
    private final UserResponseDTO user;

    public ResponseDTO(String JWTToken, String tokenType, String message, long expiredDate, UserResponseDTO user) {
        this.JWTToken = JWTToken;
        this.TokenType = tokenType;
        this.message = message;
        this.ExpiredDate = expiredDate;
        this.user = user;
    }
}
