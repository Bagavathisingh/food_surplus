package com.pm.securitybackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class LoginRequestDto {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDate updateAt;
}
