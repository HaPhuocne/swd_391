package com.fpt.swd391.fall2022.swd391.api_cart.repositories;

import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<Product,Long> {
}
