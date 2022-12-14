package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SystemCategoryService {
    SystemCategoryResponse createCategory(SystemCategoryRequest categoryRequest);
    SystemCategoryResponse updateNameCategory(SystemCategoryRequest categoryRequest, Long id);

    List<SystemCategoryResponse> getAllCategory();
    boolean deleteCategory(Long id);
    List<SystemCategoryResponse> findSystemCategories(int pageNo,int pageSize);

     PageResponse<SystemCategoryResponse> listFilterSearchPaging(int pageNo, int pageSize) ;
    ResponseEntity<?> findById(Long id);

}
