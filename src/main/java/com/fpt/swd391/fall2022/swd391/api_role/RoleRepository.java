package com.fpt.swd391.fall2022.swd391.api_role;


import com.fpt.swd391.fall2022.swd391.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> getById(long id);
}