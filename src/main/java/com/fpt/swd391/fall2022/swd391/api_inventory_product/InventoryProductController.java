package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/inventory-products")
public class InventoryProductController{
    final
    InventoryProductService inventoryProductService;

    public InventoryProductController(InventoryProductService inventoryProductService) {
        this.inventoryProductService = inventoryProductService;
    }

    @PostMapping
    InventoryProductResponse addInventoryProduct(@Valid @RequestBody InventoryProductRequest inventoryProductRequest){
        return inventoryProductService.addInventoryProduct(inventoryProductRequest);
    }
}
