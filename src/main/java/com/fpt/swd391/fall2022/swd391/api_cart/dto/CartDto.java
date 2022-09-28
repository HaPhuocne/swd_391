package com.fpt.swd391.fall2022.swd391.api_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {
    private Long idProduct;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private int size;
    private String color;
    private String URL;
    private  Long idAccount;

}
