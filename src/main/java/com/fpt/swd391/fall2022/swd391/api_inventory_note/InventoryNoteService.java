package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryNoteService {
    InventoryNoteResponse addInventoryNote(Long idShop,Long idWareHouse,InventoryNoteRequest inventoryNoteRequest);
    List<InventoryNoteResponse> getAllInventory();
    boolean deleteInventoryNote(Long id);
}
