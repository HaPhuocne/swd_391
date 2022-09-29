package com.fpt.swd391.fall2022.swd391.api_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/{idAccount}")
    ShopResponse addNewShop(@PathVariable Long idAccount, @Valid @RequestBody ShopRequest shopRequest) {
        return shopService.addShop(idAccount,shopRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteShop(@PathVariable Long id){
        if(shopService.deleteShop(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }
}
