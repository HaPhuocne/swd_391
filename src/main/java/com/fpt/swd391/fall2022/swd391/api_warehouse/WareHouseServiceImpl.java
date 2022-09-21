package com.fpt.swd391.fall2022.swd391.api_warehouse;

import com.fpt.swd391.fall2022.swd391.entity.WareHouse;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import java.util.List;

public class WareHouseServiceImpl implements WareHouseService{
    final
    WareHouesRepository wareHouesRepository;

    final
    ModelMapper modelMapper;

    public WareHouseServiceImpl(WareHouesRepository wareHouesRepository, ModelMapper modelMapper) {
        this.wareHouesRepository = wareHouesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WareHouseResponse updateQuantityWareHouse(Long id, WareHouseRequest wareHouseRequest) {
        WareHouse oldWareHouse = wareHouesRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("not found warehouse")
        );
        modelMapper.map(wareHouseRequest,oldWareHouse);
        wareHouesRepository.save(oldWareHouse);
        return modelMapper.map(oldWareHouse, WareHouseResponse.class);
    }
}
