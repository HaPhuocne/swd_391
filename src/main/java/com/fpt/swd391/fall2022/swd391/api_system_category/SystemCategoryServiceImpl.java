package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SystemCategoryServiceImpl implements SystemCategoryService{
    final
    SystemCategoryRepository categoryRepository;
    final
    ModelMapper modelMapper;

    public SystemCategoryServiceImpl(SystemCategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SystemCategoryResponse createCategory(SystemCategoryRequest categoryRequest) {
        SystemCategory systemCategory = modelMapper.map(categoryRequest,SystemCategory.class);
        Optional<SystemCategory> categoryOptional = categoryRepository.findByName(categoryRequest.getName());
        if(categoryOptional.isPresent()){
            throw new ResourceNotFoundException("SystemCategory is already existed. Please enter a different SystemCategory");
        }
        systemCategory.setStatus(true);
        categoryRepository.save(systemCategory);
        return modelMapper.map(systemCategory,SystemCategoryResponse.class);
    }

    @Override
    public SystemCategoryResponse updateNameCategory(SystemCategoryRequest categoryRequest, Long id) {
        SystemCategory systemCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found System Category")
        );
        Optional<SystemCategory> categoryOptional = categoryRepository.findByName(categoryRequest.getName());
        if(!categoryOptional.isPresent() || systemCategory.getName().equals(categoryRequest.getName()) ){
            modelMapper.map(categoryRequest, systemCategory);
            categoryRepository.save(systemCategory);
            return modelMapper.map(systemCategory, SystemCategoryResponse.class);
        }
        throw new ResourceNotFoundException("SystemCategory is already existed. Please enter a different SystemCategory");
    }

    @Override
    public List<SystemCategoryResponse> getAllCategory() {
        List<SystemCategory> categoryList = categoryRepository.getAllCategoryByStatus();
        if(categoryList.isEmpty()){
            throw new ResourceNotFoundException("SystemCategory not found");
        }
        List<SystemCategoryResponse> hotelResponseList = new ArrayList<>();
        categoryList.forEach(h -> hotelResponseList.add(modelMapper.map(h,SystemCategoryResponse.class)));
        return hotelResponseList;
    }

    @Override
    public boolean deleteCategory(Long id) {
        SystemCategory systemCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SystemCategory not found"));
        if (!systemCategory.isStatus()){
            throw new ForbiddenException("SystemCategory already disable");
        }
        systemCategory.setStatus(false);
        categoryRepository.save(systemCategory);
        return true;
    }
}
