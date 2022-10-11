package com.fpt.swd391.fall2022.swd391.api_role;


import com.fpt.swd391.fall2022.swd391.entity.Role;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value = "SELECT s.* from role s WHERE s.name_role = :name ",nativeQuery = true)
	Optional<Role> findByName(String name);
	Optional<Role> getById(long id);
}