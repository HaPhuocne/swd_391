package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "InventoryProduct Controller", description = "API liên quan đến InventoryProduct")
@RestController
@RequestMapping("/inventory-products")
public class InventoryProductController{
    final
    InventoryProductService inventoryProductService;

    public InventoryProductController(InventoryProductService inventoryProductService) {
        this.inventoryProductService = inventoryProductService;
    }

    @Operation(
            summary = "Thêm thông tin inventoryProduct ",
            description = "trả về thông tin inventoryProduct được thêm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @PostMapping("/{idWareHouse}/{idProduct}/{idInventoryNote}")
    InventoryProductResponse addInventoryProduct(@Valid @RequestBody InventoryProductRequest inventoryProductRequest, @PathVariable Long idWareHouse,@PathVariable Long idProduct,@PathVariable Long idInventoryNote){
        return inventoryProductService.addInventoryProduct(inventoryProductRequest, idWareHouse, idProduct, idInventoryNote);
    }
}
