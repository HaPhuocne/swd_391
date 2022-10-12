package com.fpt.swd391.fall2022.swd391.api_product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Product Controller", description = "API liên quan đến Product")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    final
    ProductService productService;



    @Operation(
            summary = "Tạo 1 product mới ",
            description = "Tạo 1 product mới ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400", description = "Truyền sai dữ liệu"),

            }

    )
    @PostMapping("/{idShop}")
    ProductResponse addProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long idShop) {
        return productService.createProduct(productRequest, idShop);
    }
    @Operation(
            summary = "Thay đổi thông tin product ",
            description = "truyền id product muốn thay đổi",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400", description = "Truyền sai dữ liệu"),

            }
    )
    @PutMapping("/{id}")
    ProductResponse updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id) {
        return productService.updateProduct(productRequest, id);
    }

    @Operation(
            summary = "Xóa 1 product khi truyền id product ",
            description = "Xóa 1 product khi truyền id product"
            ,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400", description = "Truyền sai dữ liệu"),

            }

    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.BAD_REQUEST);
    }

    @Operation(
            summary = "search thông tin product ",
            description = "trả về thông tin các product được search" +
                    "pageNo là số phần tử hiện trong 1 trang , pageSize là số trang",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400", description = "Truyền sai dữ liệu"),

            }

    )
    @GetMapping("")
      ProductPageResponse<ProductResponse> ListContentSearchProduct(@RequestParam(required = false) String content, int pageNo, int pageSize) {
        return productService.ListContentSearchProduct(content, pageSize, pageNo);
    }

    @Operation(
            summary = "Search Thông tin Product bằng Id ",
            description = "truyền id Product muốn tìm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400", description = "Truyền sai dữ liệu"),

            }

    )
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

}
