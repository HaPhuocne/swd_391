package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import org.springframework.stereotype.Service;

@Service
public interface InventoryProductService {
    InventoryProductResponse addInventoryProduct(InventoryProductRequest inventoryProductRequest,Long idWareHouse,Long idProduct,Long idInventoryNote);

}
