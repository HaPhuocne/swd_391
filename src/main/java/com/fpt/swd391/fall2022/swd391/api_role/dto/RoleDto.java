package com.fpt.swd391.fall2022.swd391.api_role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    @NotBlank(message = "name_role is mandatory")
    @NotNull(message = "name_role not null")
    private String name_role;
}
