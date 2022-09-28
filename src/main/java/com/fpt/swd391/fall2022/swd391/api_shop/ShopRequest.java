package com.fpt.swd391.fall2022.swd391.api_shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    String address;
    String name;
}
