package com.fpt.swd391.fall2022.swd391.api_system_category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemCategoryResponse {
    Long id;
    String name;
    int rank;
}
