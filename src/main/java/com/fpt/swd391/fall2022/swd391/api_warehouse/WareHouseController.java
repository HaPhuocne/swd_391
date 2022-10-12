package com.fpt.swd391.fall2022.swd391.api_warehouse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/warehouses")
public class WareHouseController {
    WareHouseService wareHouseService;
    @Operation(
            summary = "Thêm thông tin wareHouse ",
            description = "trả về thông tin wareHouse được thêm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @PutMapping("/{id}")
    WareHouseResponse updateQuantityWareHouse(@PathVariable Long id,@Valid @RequestBody WareHouseRequest wareHouseRequest){
        return wareHouseService.updateQuantityWareHouse(id,wareHouseRequest);
    }
}
