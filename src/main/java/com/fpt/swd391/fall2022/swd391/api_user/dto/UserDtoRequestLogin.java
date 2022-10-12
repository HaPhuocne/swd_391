package com.fpt.swd391.fall2022.swd391.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UserDtoRequestLogin {
    @Column(unique = true)
    @NotBlank(message = "email is mandatory")
    @Email
    @Pattern(regexp = "^[\\w-\\.]+@([fpt]+\\.)+[edu+\\.]+[vn]{2,4}$",
            message ="@fpt.edu.vn")
    private String email;
    //^                 # start-of-string
//(?=.*[0-9])       # a digit must occur at least once
//(?=.*[a-z])       # a lower case letter must occur at least once
//(?=.*[A-Z])       # an upper case letter must occur at least once
//(?=.*[@#$%^&+=])  # a special character must occur at least once
//(?=\S+$)          # no whitespace allowed in the entire string
//.{8,}             # anything, at least eight places though
//$                 # end-of-string
    @NotBlank(message = "password is mandatory")
    @Length(max = 128)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "a digit must occur at least once \n" +
                    "a lower case letter must occur at least once\n" +
                    "an upper case letter must occur at least once\n" +
                    "a special character must occur at least once\n" +
                    "no whitespace allowed in the entire string\n" +
                    "anything, at least eight places though.")
    private String password;
}
