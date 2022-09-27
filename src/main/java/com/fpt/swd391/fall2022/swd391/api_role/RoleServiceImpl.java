package com.fpt.swd391.fall2022.swd391.api_role;

import com.fpt.swd391.fall2022.swd391.api_role.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.api_role.dto.RoleDto;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
        return ResponseEntity.ok(new MessageResponse("Ok",roleRepository.save(modelMapper.map(roleDto, Role.class))));
    }

    @Override
    public ResponseEntity<?> getAll() {
        roleRepository.findAll();
        return ResponseEntity.ok().body(new MessageResponse("List",null));
    }
}
