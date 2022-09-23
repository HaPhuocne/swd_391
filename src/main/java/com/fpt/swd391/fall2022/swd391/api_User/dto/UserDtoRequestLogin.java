package com.fpt.swd391.fall2022.swd391.api_User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor@NoArgsConstructor
public class UserDtoRequestLogin {
    private String email;
    private String password;
}
