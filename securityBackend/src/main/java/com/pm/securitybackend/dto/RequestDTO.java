package com.pm.securitybackend.dto;


import com.pm.securitybackend.dto.validator.CreateUserValidationGroup;
import com.pm.securitybackend.model.Role.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class RequestDTO {
    @NotNull
    private String name;

    @Email()
    @NotNull
    private String email;

    @NotNull(groups = CreateUserValidationGroup.class ,message = "the password field is required !")
    private String password;

    @NotNull
    private String phone;

    @NotNull(groups = CreateUserValidationGroup.class ,message = "the Role field is required !")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(groups = CreateUserValidationGroup.class ,message = "the Verified state is required !")
    private Boolean verified;

    @NotNull(groups = CreateUserValidationGroup.class ,message = "the Created date is required !")
    private LocalDate createdAt;

    @NotNull
    private LocalDate updateAt;

}
