package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "InventoryNote Controller", description = "API liên quan đến InventoryNote")
@RestController
@RequestMapping("/inventory-notes")
public class InventoryNoteController {
    final
    InventoryNoteService inventoryNoteService;

    public InventoryNoteController(InventoryNoteService inventoryNoteService) {
        this.inventoryNoteService = inventoryNoteService;
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
    @PostMapping("/{idShop}/{idWareHouse}")
    InventoryNoteResponse addInventoryNote(@PathVariable Long idShop, @PathVariable Long idWareHouse, @Valid @RequestBody InventoryNoteRequest inventoryNoteRequest) {
        return inventoryNoteService.addInventoryNote(idShop, idWareHouse, inventoryNoteRequest);
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
    @GetMapping
    List<InventoryNoteResponse> getAllInventoryNote() {
        return inventoryNoteService.getAllInventory();
    }

    @Operation(
            summary = "Xóa Inventory note ",
            description = "trả về thông tin inventoryProduct được thêm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteInventoryNote(@PathVariable Long id) {
        if (inventoryNoteService.deleteInventoryNote(id)) {
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL", null, HttpStatus.BAD_REQUEST);
    }
}
