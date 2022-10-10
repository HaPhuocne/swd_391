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
@RequestMapping("/Informations")
public class InformationController {
    final
    UserService userService;

    public InformationController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "search thông tin account ",
            description = "trả về thông tin các account được search",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),

            }

    )
    @GetMapping("")
    PageResponse<InformationUserDtoResponse> listFilterSearchPaging(@RequestParam(required = false) String content, int pageNo, int pageSize) {
        return userService.listFilterSearchPaging(content, pageSize, pageNo);
    }
    @Operation(
            summary = "Search Thông tin account bằng Id ",
            description = "truyền id account muốn tìm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),

            }

    )
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id)
    {
        return userService.findById(id);
    }
    @Operation(
            summary = "Thay đổi thông tin account ",
            description = "truyền id account muốn thay đổi",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),

            }
    )
    @PostMapping("/{id}")
    ResponseEntity<?> updateInformation(@RequestBody @Valid InformationUserDtoRequest request, @Valid @PathVariable Long id) {
        return userService.UpdateInformation(request, id);
    }
    @Operation(
            summary = "Xóa account ",
            description = "truyền id account muốn xóa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),

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
                    @ApiResponse(responseCode = "403", description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),

            }
    )
    @PutMapping("/changepassword/{id}")
    ResponseEntity<?> changePassword(@RequestBody @Valid PasswordDtoRequest request, @Valid @PathVariable Long id) {
        return userService.ChangePassword(request, id);
    }
}
