package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import com.fpt.swd391.fall2022.swd391.api_inventory_note.InventoryNoteResponse;
import com.fpt.swd391.fall2022.swd391.api_product.ProductResponse;
import com.fpt.swd391.fall2022.swd391.api_warehouse.WareHouseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductResponse {
    int quantity;
    ProductResponse productResponse;
    InventoryNoteResponse inventoryNoteResponse;
    WareHouseResponse wareHouseResponse;
}
