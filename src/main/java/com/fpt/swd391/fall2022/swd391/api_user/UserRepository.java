package com.fpt.swd391.fall2022.swd391.api_user;

import com.fpt.swd391.fall2022.swd391.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface UserRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);

    @Query(value = "select a from Account a " +
            "where (lower(a.email) like lower(concat('%', :filter,'%'))) or (lower(a.fullName) like(lower(concat('%', :filter,'%'))))" +
            " or (lower(a.phone) like (lower(concat('%', :filter,'%')))) or (lower(a.address) like (lower(concat('%', :filter,'%'))))")
    Page<Account> listFilterSearchPaging
            (String filter, Pageable pageable);
}
