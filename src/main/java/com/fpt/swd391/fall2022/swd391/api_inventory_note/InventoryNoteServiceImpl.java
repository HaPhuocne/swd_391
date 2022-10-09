package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.api_warehouse.WareHouesRepository;
import com.fpt.swd391.fall2022.swd391.api_warehouse.WareHouseResponse;
import com.fpt.swd391.fall2022.swd391.entity.InventoryNote;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.entity.WareHouse;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryNoteServiceImpl implements InventoryNoteService{
    final
    ModelMapper modelMapper;
    final
    InventoryNoteRepository inventoryNoteRepository;
    final
    ShopRepository shopRepository;
    final
    WareHouesRepository wareHouesRepository;

    public InventoryNoteServiceImpl(ModelMapper modelMapper, InventoryNoteRepository inventoryNoteRepository, ShopRepository shopRepository, WareHouesRepository wareHouesRepository) {
        this.modelMapper = modelMapper;
        this.inventoryNoteRepository = inventoryNoteRepository;
        this.shopRepository = shopRepository;
        this.wareHouesRepository = wareHouesRepository;
    }

    @Override
    public InventoryNoteResponse addInventoryNote(Long idShop, Long idWareHouse, InventoryNoteRequest inventoryNoteRequest) {
        InventoryNote inventoryNote = modelMapper.map(inventoryNoteRequest,InventoryNote.class);

        Shop shop = shopRepository.findById(idShop).orElseThrow(
                () -> new ResourceNotFoundException("Not found Shop")
        );
        WareHouse wareHouse = wareHouesRepository.findById(idWareHouse).orElseThrow(
                () -> new ResourceNotFoundException("Not found WareHouse")
        );
        inventoryNote.setShopName(shop.getName());
        inventoryNote.setShop(shop);
        inventoryNote.setWareHouse(wareHouse);
        modelMapper.map(wareHouse, WareHouseResponse.class);
        wareHouesRepository.save(wareHouse);
        inventoryNote.setStatus(true);
        inventoryNoteRepository.save(inventoryNote);
        return modelMapper.map(inventoryNote,InventoryNoteResponse.class);
    }

    @Override
    public List<InventoryNoteResponse> getAllInventory() {
        List<InventoryNote> inventoryNoteList= inventoryNoteRepository.getAllByStatus();
        if (inventoryNoteList.isEmpty()){
            throw new ResourceNotFoundException("Not found InventoryNote");
        }
        List<InventoryNoteResponse> inventoryNoteResponseList = new ArrayList<>();
        inventoryNoteList.forEach(i -> inventoryNoteResponseList.add(modelMapper.map(i,InventoryNoteResponse.class)));

        return inventoryNoteResponseList;
    }

    @Override
    public boolean deleteInventoryNote(Long id) {
        InventoryNote inventoryNote = inventoryNoteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found InventoryNote")
        );
        inventoryNote.setStatus(false);
        inventoryNoteRepository.save(inventoryNote);
        return true;
    }
}
