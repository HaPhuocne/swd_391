package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    ResponseEntity<?> createNewRole(RoleDto roleDto);
   List<Role> getAll();

}
