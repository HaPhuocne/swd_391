package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationUserDtoRequest {
    @NotBlank(message = "fullName is mandatory")
    private String fullName;

    @Max(10)
    private String phone;

    private String address;

    private String image;


}
