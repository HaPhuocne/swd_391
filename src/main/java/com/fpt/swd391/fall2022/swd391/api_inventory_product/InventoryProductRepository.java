package com.fpt.swd391.fall2022.swd391.api_inventory_product;

import com.fpt.swd391.fall2022.swd391.entity.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductRepository extends JpaRepository<InventoryProduct,Long> {
}
