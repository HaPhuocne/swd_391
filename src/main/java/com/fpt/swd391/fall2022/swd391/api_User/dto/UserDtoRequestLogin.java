package com.fpt.swd391.fall2022.swd391.api_User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UserDtoRequestLogin {
    private String email;
    private String password;
}
