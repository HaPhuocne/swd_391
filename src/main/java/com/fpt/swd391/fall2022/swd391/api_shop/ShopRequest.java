package com.fpt.swd391.fall2022.swd391.api_shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    @NotBlank(message = "name is mandatory")
    @NotNull(message = "Address SHop not null")
    String address;
    String name;
}
