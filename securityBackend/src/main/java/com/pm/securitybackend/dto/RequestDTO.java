package com.pm.securitybackend.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDTO {
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String CreatedAt;

}
