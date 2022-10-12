package com.fpt.swd391.fall2022.swd391.api_shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Shop Controller", description = "API liên quan đến Shop")
@RestController
@RequestMapping("/shops")
public class ShopController {
    final
    ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @Operation(
            summary = "Thêm thông tin shop ",
            description = "trả về thông tin các shop được thêm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @PostMapping("/{idAccount}")
    ShopResponse addNewShop(@PathVariable Long idAccount, @Valid @RequestBody ShopRequest shopRequest) {
        return shopService.addShop(idAccount,shopRequest);
    }

    @Operation(
            summary = "Xóa shop ",
            description = "truyền id shop muốn xóa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteShop(@PathVariable Long id){
        if(shopService.deleteShop(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }
}
