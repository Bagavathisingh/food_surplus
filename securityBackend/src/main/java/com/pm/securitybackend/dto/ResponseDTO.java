package com.pm.securitybackend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pm.securitybackend.model.AppUser;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonPropertyOrder({
        "JWTToken",
        "TokenType",
        "message",
        "user"
})
public class ResponseDTO {
    @NotNull
    private final String JWTToken;

    @NotNull
    private final String TokenType;

    @NotNull
    private final String message;

    @NotNull
    private final UserResponseDTO user;

    public ResponseDTO(String JWTToken, String tokenType, String message, UserResponseDTO user) {
        this.JWTToken = JWTToken;
        this.TokenType = tokenType;
        this.message = message;
        this.user = user;
    }
}
