package com.fpt.swd391.fall2022.swd91.api_system_category;

import com.fpt.swd391.fall2022.swd91.entity.SystemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemCategoryRepository extends JpaRepository<SystemCategory,Long> {
}
