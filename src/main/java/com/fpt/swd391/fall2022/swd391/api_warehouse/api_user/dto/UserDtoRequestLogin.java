package com.fpt.swd391.fall2022.swd391.api_warehouse.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UserDtoRequestLogin {
    private String email;
    private String password;
}
