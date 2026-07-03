package com.pm.securitybackend.model;

import com.pm.securitybackend.model.Role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;


    @Email
    @NotNull
    private String email;


    @NotNull
    private String password;

    @NotNull
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


    private Boolean verified;


    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updateAt;
}
