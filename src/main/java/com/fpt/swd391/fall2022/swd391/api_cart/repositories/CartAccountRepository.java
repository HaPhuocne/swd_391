package com.fpt.swd391.fall2022.swd391.api_cart.repositories;

import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartAccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);



    @Query("Select a.cartItemCollection from Account a where a.id = :idAccount ")
     List<Cart> findCartByIdAccount(Long idAccount);
}
