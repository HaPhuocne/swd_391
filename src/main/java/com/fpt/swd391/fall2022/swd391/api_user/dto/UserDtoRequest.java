package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String image;

    private String role ;
    private boolean status ;
}
