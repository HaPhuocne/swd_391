package com.fpt.swd391.fall2022.swd391.api_shop;

import com.fpt.swd391.fall2022.swd391.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
}
