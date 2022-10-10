package com.fpt.swd391.fall2022.swd391.api_user.controllers;

import com.fpt.swd391.fall2022.swd391.api_user.UserService;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoRequestLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Tag(name = "Auth Controller", description = "API liên quan đến đăng nhập và đăng kí tài khoản")
@RestController
@RequestMapping
@RequiredArgsConstructor

public class AuthController {
    final
    UserService userService;
    @Operation(
            summary = "Đăng nhập tài khoản",
            description = "Đăng nhập thành công sẽ nhận được JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid UserDtoRequestLogin request) {
        return userService.Login(request);
    }
    @Operation(
            summary = "Đăng kí tài khoản",
            description = "Đăng kí tài khoản bằng gmail @fpt.edu.vn hoặc @gmail.com",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid UserDtoRequest request) {
        return userService.Register(request);
    }
}
