package com.fpt.swd391.fall2022.swd391.api_user.dto;

import com.fpt.swd391.fall2022.swd391.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
