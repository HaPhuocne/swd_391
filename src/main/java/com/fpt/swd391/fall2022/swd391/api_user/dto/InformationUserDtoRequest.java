package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationUserDtoRequest {
    @NotBlank(message = "fullName is mandatory")
    @NotNull(message = "FullName is not null")
    private String fullName;

    @Max(value = 10,message = "Max 10 numbers")
    private String phone;

    private String address;

    private String image;


}
