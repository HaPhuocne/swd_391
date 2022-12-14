package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationUserDtoResponse {


    private String email;

    private String fullName;

    private String phone;

    private String address;

    private String image;

    private boolean status;

}
