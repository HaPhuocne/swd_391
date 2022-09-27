package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class RoleServiceImpl implements RoleService{
    final RoleRepository roleRepository;
    final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> createNewRole(RoleDto roleDto) {
        roleRepository.save(modelMapper.map(roleDto, Role.class));
        return ResponseEntity.ok(new MessageResponse("Ok",roleDto));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
