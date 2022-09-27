package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    ResponseEntity<?> createNewRole(@RequestBody RoleDto roleDto) {
        return roleService.createNewRole(roleDto);
    }

    @GetMapping
    List<Role> getAll() {
        return roleService.getAll();
    }

}
