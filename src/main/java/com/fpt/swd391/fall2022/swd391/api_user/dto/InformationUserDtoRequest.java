package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationUserDtoRequest {
    @NotBlank(message = "fullName is mandatory")
    @NotNull(message = "FullName is not null")
    private String fullName;

//    @Max(value = 10,message = "Max 10 numbers")
//    @Length(max = 10,message = "Max 10 numbers")
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b",
            message = "84 or 0 +[3,5,7,8,9] " +
                    "anything, at least eight places though")
    private String phone;

    private String address;

    private String image;


}
