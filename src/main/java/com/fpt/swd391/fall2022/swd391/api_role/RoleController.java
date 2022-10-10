package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Role Controller", description = "API liên quan đến roles")

@RestController
@RequestMapping("roles")
public class RoleController {
    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @Operation(
            summary = "Tạo vai trò mới",
            description = "Tạo vai trò mới",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )

    @PostMapping
    ResponseEntity<?> createNewRole(@RequestBody RoleDto roleDto) {
        return roleService.createNewRole(roleDto);
    }
    @Operation(
            summary = "Lấy tất cả vai trò",
            description = "Lấy tất cả vai trò",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công"),
                    @ApiResponse(responseCode = "500", description = "Lỗi Server"),
                    @ApiResponse(responseCode = "403" , description = "Truyền sai dữ liệu"),
                    @ApiResponse(responseCode = "401", description = "Lỗi Phân quyền"),
                    @ApiResponse(responseCode = "400" , description = "Truyền sai dữ liệu"),

            }
    )
    @GetMapping
    List<Role> getAll() {
        return roleService.getAll();
    }

}
