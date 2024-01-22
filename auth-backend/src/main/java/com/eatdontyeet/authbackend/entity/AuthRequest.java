package com.eatdontyeet.authbackend.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthRequest {

    @NonNull
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NonNull
    @NotBlank(message = "Password cannot be blank")
    private String password;


}
