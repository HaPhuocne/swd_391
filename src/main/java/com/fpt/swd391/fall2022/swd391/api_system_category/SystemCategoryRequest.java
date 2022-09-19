package com.fpt.swd391.fall2022.swd391.api_system_category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SystemCategoryRequest {
    private String name;
    private int rank;
}
