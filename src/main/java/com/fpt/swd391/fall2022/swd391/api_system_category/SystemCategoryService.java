package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SystemCategoryService {
    SystemCategoryResponse createCategory(SystemCategoryRequest categoryRequest);
    SystemCategoryResponse updateNameCategory(SystemCategoryRequest categoryRequest, Long id);

    List<SystemCategoryResponse> getAllCategory ();
    boolean deleteCategory(Long id);
    List<SystemCategoryResponse> findSystemCategories(int pageNo,int pageSize);

}
