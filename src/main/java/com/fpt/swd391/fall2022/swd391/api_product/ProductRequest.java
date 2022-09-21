package com.fpt.swd391.fall2022.swd391.api_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    String name;
    int price;
    int quantity;
    int size;
    String deseption;
    String color;
    long idSystemCategory;
}
