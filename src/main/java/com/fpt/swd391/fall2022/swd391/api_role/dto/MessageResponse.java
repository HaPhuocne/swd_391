package com.fpt.swd391.fall2022.swd391.api_role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String message;
    private Object object;


}
