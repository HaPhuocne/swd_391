package com.fpt.swd391.fall2022.swd391.api_shop;

import com.fpt.swd391.fall2022.swd391.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
    @Query(value = "DELETE from shop s WHERE s.id = :id",nativeQuery = true)
    Optional<Shop> deleteShopById(Long id);
}
