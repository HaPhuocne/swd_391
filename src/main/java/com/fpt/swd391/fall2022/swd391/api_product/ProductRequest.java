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
    @NotNull
    String name;
    @NotBlank(message = "price is mandatory")
    @Min(1)
    int price;
    @NotBlank(message = "quantity is mandatory")
    @Min(1)
    int quantity;
    int size;
    String deception;
    String color;
    String image;
    long idSystemCategory;
}
