package com.fpt.swd391.fall2022.swd391.api_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDto {
    private Long idProduct;
    private String productName;
}

