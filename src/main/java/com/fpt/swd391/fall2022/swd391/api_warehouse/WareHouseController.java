package com.fpt.swd391.fall2022.swd391.api_warehouse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouses")
public class WareHouseController {
    WareHouseService wareHouseService;
    @PutMapping("/{id}")
    WareHouseResponse updateQuantityWareHouse(@PathVariable Long id, WareHouseRequest wareHouseRequest){
        return wareHouseService.updateQuantityWareHouse(id,wareHouseRequest);
    }
}
