package com.fpt.swd391.fall2022.swd391.api_user;

import com.fpt.swd391.fall2022.swd391.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface UserRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);
}
