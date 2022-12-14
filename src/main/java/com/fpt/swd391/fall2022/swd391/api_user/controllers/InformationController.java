package com.fpt.swd391.fall2022.swd391.api_user.controllers;


import com.fpt.swd391.fall2022.swd391.api_user.UserService;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PageResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PasswordDtoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Information Controller", description = "API liên quan đến Information Account và Xóa tài khoản")
@RestController
@RequestMapping("/informations")
public class InformationController {
    final
    UserService userService;

    public InformationController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "search thông tin account ",
            description = "trả về thông tin các account được search" +
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
    PageResponse<InformationUserDtoResponse> listFilterSearchPaging(@RequestParam(required = false) String content, int pageNo, int pageSize) {
        return userService.listFilterSearchPaging(content, pageNo, pageSize);
    }
    @Operation(
            summary = "Search Thông tin account bằng Id ",
            description = "truyền id account muốn tìm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }

    )
    @GetMapping("/{email}")
    ResponseEntity<?> findByEmail(@PathVariable String email)
    {
        return userService.findById(email);
    }
    @Operation(
            summary = "Thay đổi thông tin account ",
            description = "truyền id account muốn thay đổi",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @PostMapping("/{email}")
    ResponseEntity<?> updateInformation(@RequestBody @Valid InformationUserDtoRequest request, @Valid @PathVariable String email) {
        return userService.UpdateInformation(request, email);
    }
    @Operation(
            summary = "Xóa account ",
            description = "truyền id account muốn xóa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@Valid @PathVariable Long id) {
        return userService.Delete(id);
    }
    @Operation(
            summary = "Thay đổi password account ",
            description = "truyền id account muốn thay đổi password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @PutMapping("/change_password/{email}")
    ResponseEntity<?> changePassword(@RequestBody @Valid PasswordDtoRequest request, @Valid @PathVariable String email) {
        return userService.ChangePassword(request, email);
    }
}
