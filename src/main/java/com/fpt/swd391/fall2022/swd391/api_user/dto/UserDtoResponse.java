package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private String email;
    private Long id;
    private String token;
}
