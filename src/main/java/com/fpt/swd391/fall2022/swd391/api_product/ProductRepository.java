package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p.* FROM product p where p.status =true",nativeQuery = true)
    List<Product> getAllByStatus();
}
