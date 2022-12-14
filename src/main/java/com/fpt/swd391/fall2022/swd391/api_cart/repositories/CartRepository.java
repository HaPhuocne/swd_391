package com.fpt.swd391.fall2022.swd391.api_cart.repositories;

import com.fpt.swd391.fall2022.swd391.entity.Account;


import com.fpt.swd391.fall2022.swd391.entity.Cart;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c FROM Cart c WHERE c.account.id = :idAccount")
     List<Cart> findByIdAccount(Long idAccount);

    Cart findByAccountAndProduct(Account account, Product product);


    boolean existsByAccountAndProduct(Account account, Product product);

    List<Cart> findByAccount(Account account);

}
