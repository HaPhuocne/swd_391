package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemCategoryRepository extends JpaRepository<SystemCategory,Long> {
    @Query(value = "SELECT s.* from system_category s where s.name = :name and s.status = true",nativeQuery = true)
    Optional<SystemCategory> findByName(String name);
    @Query(value = "SELECT s.* from system_category s where s.status = true",nativeQuery = true)
    List<SystemCategory> getAllCategoryByStatus();
}
