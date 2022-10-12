package com.fpt.swd391.fall2022.swd391.api_system_category;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemCategoryRequest {
    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is not null")
    private String name;
    @Min(value = 1,message = "Min one rank")
    private int rank;
}
