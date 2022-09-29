package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p.* FROM product p where p.status =true",nativeQuery = true)
    List<Product> getAllByStatus();

    @Query(value = "SELECT p.* FROM product p where p.system_category_id = :idCategory",nativeQuery = true)
    Optional<Product> deleteProductByIdSystemCategory(Long idCategory);
}
