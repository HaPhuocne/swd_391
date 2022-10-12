package com.fpt.swd391.fall2022.swd391.api_cart.repositories;

import com.fpt.swd391.fall2022.swd391.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartShopRepository extends JpaRepository<Shop,Long> {
}
