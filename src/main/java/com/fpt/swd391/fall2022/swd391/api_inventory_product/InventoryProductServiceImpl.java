package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import com.fpt.swd391.fall2022.swd391.api_inventory_note.InventoryNoteRepository;
import com.fpt.swd391.fall2022.swd391.api_product.ProductRepository;
import com.fpt.swd391.fall2022.swd391.api_warehouse.WareHouesRepository;
import com.fpt.swd391.fall2022.swd391.entity.InventoryNote;
import com.fpt.swd391.fall2022.swd391.entity.InventoryProduct;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.WareHouse;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryProductServiceImpl implements InventoryProductService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    InventoryProductRepository inventoryProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InventoryNoteRepository inventoryNoteRepository;
    @Autowired
    WareHouesRepository wareHouesRepository;

    @Override
    public InventoryProductResponse addInventoryProduct(InventoryProductRequest inventoryProductRequest,Long idWareHouse,Long idProduct,Long idInventoryNote) {
        InventoryProduct inventoryProduct = modelMapper.map(inventoryProductRequest,InventoryProduct.class);
        Product product = productRepository.findById(idProduct).orElseThrow(
                () -> new ResourceNotFoundException("Not found Product")
        );
        WareHouse wareHouse = wareHouesRepository.findById(idWareHouse).orElseThrow(
                () -> new ResourceNotFoundException("Not found WareHouse")
        );
        InventoryNote inventoryNote = inventoryNoteRepository.findById(idInventoryNote).orElseThrow(
                () -> new ResourceNotFoundException("Not found InventoryNote")
        );
        inventoryProduct.setProduct(product);
        inventoryProduct.setInventoryNote(inventoryNote);
        inventoryProduct.setWareHouse(wareHouse);
        inventoryProductRepository.save(inventoryProduct);
        return modelMapper.map(inventoryProduct,InventoryProductResponse.class);
    }
}

