package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Tag(name = "Category Controller", description = "API liên quan đến Category")

@RestController
@RequestMapping("/system-categories")
public class SystemCategoryController {
    final
    SystemCategoryService systemCategoryService;

    public SystemCategoryController(SystemCategoryService systemCategoryService) {
        this.systemCategoryService = systemCategoryService;
    }
    @Operation(
            summary = "Tạo mới 1 category ",
            description = "Tạo mới 1 category"
            ,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @PostMapping
    SystemCategoryResponse createSystemCategory(@Valid @RequestBody SystemCategoryRequest systemCategoryRequest){
        return systemCategoryService.createCategory(systemCategoryRequest);
    }
    @Operation(
            summary = "Update 1 category bằng id category ",
            description = "Update 1 category bằng id category"
            ,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @PutMapping("/{id}")
    SystemCategoryResponse updateNameSystemCategory(@Valid @RequestBody SystemCategoryRequest systemCategoryRequest,@PathVariable Long id){
        return systemCategoryService.updateNameCategory(systemCategoryRequest,id);
    }

//    @GetMapping
//    List<SystemCategoryResponse> getAll(){
//        return systemCategoryService.getAllCategory();
//    }

    @Operation(
            summary = "Delete 1 category bằng id category ",
            description = "Xóa 1 category bằng id category"
                   ,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id){
        if (systemCategoryService.deleteCategory(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }
//    @GetMapping("/{pageNo}/{pageSize}")
//    List<SystemCategoryResponse> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
//        return systemCategoryService.findSystemCategories(pageNo,pageSize);
//    }

    @Operation(
            summary = "list thông tin category ",
            description = "trả về thông tin các category" +
                    "pageNo là số phần tử hiện trong 1 trang , pageSize là số trang",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @GetMapping("")
    PageResponse<SystemCategoryResponse> listFilterSearchPaging( int pageNo, int pageSize) {
        return systemCategoryService.listFilterSearchPaging(pageNo,pageSize);
    }

    @Operation(
            summary = "Search Thông tin Category bằng Id ",
            description = "truyền id Category muốn tìm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id)
    {
        return systemCategoryService.findById(id);
    }

    @GetMapping("/getAllCategory")
    List<SystemCategoryResponse> getAllCategory(){
        return systemCategoryService.getAllCategory();
    }
}
