package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.api_user.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PageResponse;
import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<SystemCategoryResponse> systemCategoryResponseList = new ArrayList<>();
        categoryList.forEach(h -> systemCategoryResponseList.add(modelMapper.map(h,SystemCategoryResponse.class)));
        return systemCategoryResponseList;
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

    public List<SystemCategoryResponse> findSystemCategories(int pageNo,int pageSize){
    Pageable pageable = PageRequest.of(pageNo,pageSize);
    Page<SystemCategory> systemCategoryPage = categoryRepository.findAll(pageable);
    List<SystemCategoryResponse> systemCategoryResponseList = new ArrayList<>();
    systemCategoryPage.forEach(h -> systemCategoryResponseList.add(modelMapper.map(h,SystemCategoryResponse.class)));
    return systemCategoryResponseList;
    }

    @Override
    public PageResponse<SystemCategoryResponse> listFilterSearchPaging(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<SystemCategory> systemCategoryPage = categoryRepository.findAll(pageable);
        if (systemCategoryPage.isEmpty()) {
            throw new ResourceNotFoundException("No Page");
        }

        return new PageResponse<SystemCategoryResponse>()
                .setPageSize(systemCategoryPage.getSize())
                .setTotalSize(systemCategoryPage.getTotalElements())
                .setList(systemCategoryPage.getContent()
                        .stream()
                        .map(a -> modelMapper.map(a, SystemCategoryResponse.class))
                        .collect(Collectors.toList()))
                .setPageNumber(systemCategoryPage.getNumber());


    }

    @Override
    public ResponseEntity<?> findById(Long id) {
            Optional<SystemCategory> category = categoryRepository.findById(id);
            if (category.isPresent()) {
                if (category.get().isStatus()) {
                    return ResponseEntity.ok().body(new MessageResponse("Search successful", modelMapper.map(category.get(), SystemCategoryResponse.class)));
                }
                throw new ResourceNotFoundException("Category found");
            }
            throw new ResourceNotFoundException("Category found");
        }

}
