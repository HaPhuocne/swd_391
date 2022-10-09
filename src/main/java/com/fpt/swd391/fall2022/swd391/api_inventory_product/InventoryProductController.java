package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inventory-products")
public class InventoryProductController{
    final
    InventoryProductService inventoryProductService;

    public InventoryProductController(InventoryProductService inventoryProductService) {
        this.inventoryProductService = inventoryProductService;
    }

    @PostMapping("/{idWareHouse}/{idProduct}/{idInventoryNote}")
    InventoryProductResponse addInventoryProduct(@Valid @RequestBody InventoryProductRequest inventoryProductRequest, @PathVariable Long idWareHouse,@PathVariable Long idProduct,@PathVariable Long idInventoryNote){
        return inventoryProductService.addInventoryProduct(inventoryProductRequest, idWareHouse, idProduct, idInventoryNote);
    }
}
