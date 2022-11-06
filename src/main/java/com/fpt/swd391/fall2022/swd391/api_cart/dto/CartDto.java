package com.fpt.swd391.fall2022.swd391.api_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @Min(value = 1, message = "Quantity >= 1")
    private int quantity;

    private Long  idAccount;

    private Long idProduct;


}
