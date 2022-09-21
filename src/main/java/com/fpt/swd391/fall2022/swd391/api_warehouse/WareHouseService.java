package com.fpt.swd391.fall2022.swd391.api_warehouse;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WareHouseService {
    WareHouseResponse updateQuantityWareHouse(Long id, WareHouseRequest wareHouseRequest);
}
