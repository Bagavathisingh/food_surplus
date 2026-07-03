package com.pm.securitybackend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "createdAt",
        "updateAt"
})
public class GetAllUsersDto {
    @Id
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updateAt;
}
