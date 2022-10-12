package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductRequest {
    @Min(1)
    int quantity;
}
