package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select p.* from product p \n" +
            "join system_category s on s.id = p.system_category_id \n" +
            "where \n" +
            "(p.status = true and (lower(p.\"name\")  like lower(concat('%',:content,'%'))) ) \n" +
            "or (p.status = true and (lower(s.name) like lower(concat('%',:content,'%'))) )",nativeQuery = true)
    Page<Product> searchProductBy(String content, Pageable pageable);

    List<Product> getProductByShopName(String name);

    List<Product> getProductBySystemCategoryName(String name);

    Optional<Product> findProductByIdAndStatusIsTrue(Long id);
}
