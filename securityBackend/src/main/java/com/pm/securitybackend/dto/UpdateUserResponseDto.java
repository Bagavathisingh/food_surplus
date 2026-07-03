package com.pm.securitybackend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({
    "message",
    "id",
        "email",
        "phone",
        "updateAt"
})
public class UpdateUserResponseDto {
    @Id
    private UUID id;

    @NotNull
    private String name;


    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String updateAt;

    @NotNull
    private String message;

}

