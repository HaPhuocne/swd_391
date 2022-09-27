package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    ResponseEntity<?> createNewRole(RoleDto roleDto);
    ResponseEntity<?> getAll();

}
