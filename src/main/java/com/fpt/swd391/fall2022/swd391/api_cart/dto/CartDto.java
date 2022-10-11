package com.fpt.swd391.fall2022.swd391.api_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class CartDto {

    private Long idCart;

    @Min(value = 1, message = "Quantity >= 1")
    private int quantity;

    private Long  idAccount;

    private ProductCartDto product;


}
