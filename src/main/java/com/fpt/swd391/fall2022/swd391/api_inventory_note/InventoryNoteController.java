package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inventory-notes")
public class InventoryNoteController {
    @Autowired
    InventoryNoteService inventoryNoteService;

    @PostMapping("/{idShop}/{idWareHouse}")
    InventoryNoteResponse addInventoryNote(@PathVariable Long idShop, @PathVariable Long idWareHouse, @Valid @RequestBody InventoryNoteRequest inventoryNoteRequest){
        return inventoryNoteService.addInventoryNote(idShop,idWareHouse,inventoryNoteRequest);
    }

    @GetMapping
    List<InventoryNoteResponse> getAllInventoryNote(){
        return inventoryNoteService.getAllInventory();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteInventoryNote(@PathVariable Long id){
        if (inventoryNoteService.deleteInventoryNote(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }
}
