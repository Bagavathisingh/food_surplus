package com.pm.securitybackend.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @Id
    @NotNull
    UUID id;

    @NotNull
    String name;

    @NotNull
    @Email
    String email;

    @NotNull
    String CreatedAt;
}
