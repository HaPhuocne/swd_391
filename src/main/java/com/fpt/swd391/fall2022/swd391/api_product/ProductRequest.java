package com.fpt.swd391.fall2022.swd391.api_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "Not null name")
    @NotBlank(message = "name is mandatory")
    String name;
    @Min(value = 1,message = "Min one quantity")
    int price;
    @Min(value = 1,message = "Min one quantity")
    int quantity;
    String size;
    String deception;
    String color;
    String image;
    long idSystemCategory;
}
