package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {
    @NotNull
    @Column(unique = true)
    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([fpt]+\\.)+[edu+\\.]+[vn]{2,4}$",
            message ="@fpt.edu.vn")
    @Email
    private String email;
    @NotNull
    @NotBlank(message = "password is mandatory")
    @Length(max = 128,message = "Length max 128 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "a digit must occur at least once \n" +
                    "a lower case letter must occur at least once\n" +
                    "an upper case letter must occur at least once\n" +
                    "a special character must occur at least once\n" +
                    "no whitespace allowed in the entire string\n" +
                    "anything, at least eight places though.")
    private String password;
    @NotNull(message = "fullName is not null")
    @NotBlank(message = "fullName is mandatory")
    private String fullName;
    @NotNull
    @Max(value = 10,message = "Max 10 numbers")
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String image;

    private boolean status ;
}
