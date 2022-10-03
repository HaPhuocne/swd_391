package com.fpt.swd391.fall2022.swd391.api_system_category;

import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SystemCategoryServiceImplTest {
    SystemCategoryServiceImpl systemCategoryService;
    SystemCategoryRepository systemCategoryRepository;
    ModelMapper modelMapper;

    SystemCategory systemCategory;
    SystemCategory optionalSystemCategory;
    SystemCategory expectedSystemCategory;

    SystemCategoryRequest systemCategoryRequest;
    SystemCategoryResponse systemCategoryResponse;

    List<SystemCategory> systemCategoryList;

    @BeforeEach
    void BeforeEach(){
        systemCategoryRepository = mock(SystemCategoryRepository.class);
        modelMapper = mock(ModelMapper.class);
        systemCategoryService = new SystemCategoryServiceImpl(systemCategoryRepository,modelMapper);
        systemCategoryRequest = mock(SystemCategoryRequest.class);
        systemCategoryResponse = mock(SystemCategoryResponse.class);
        systemCategory = mock(SystemCategory.class);
        optionalSystemCategory = mock(SystemCategory.class);
        expectedSystemCategory = mock(SystemCategory.class);
        systemCategoryList = mock(List.class);
    }

    @Test
    void createSystemCategory_ShouldReturnObject_WhenDataValid(){
        when(modelMapper.map(systemCategoryRequest,SystemCategory.class)).thenReturn(systemCategory);
        when(systemCategoryRepository.findByName("Test")).thenReturn(Optional.of(optionalSystemCategory));
        when(systemCategory.isStatus()).thenReturn(true);
        when(systemCategoryRepository.save(systemCategory)).thenReturn(systemCategory);
        when(modelMapper.map(systemCategory,SystemCategoryResponse.class)).thenReturn(systemCategoryResponse);
        SystemCategoryResponse result = systemCategoryService.createCategory(systemCategoryRequest);
        assertThat(result,is(systemCategoryResponse));
    }
    @Test
    void createSystemCategory_ShouldReturnObject_WhenDataIsPresent(){
        when(modelMapper.map(systemCategoryRequest,SystemCategory.class)).thenReturn(systemCategory);
        when(systemCategoryRepository.findByName(systemCategoryRequest.getName())).thenReturn(Optional.of(optionalSystemCategory));
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> systemCategoryService.createCategory(systemCategoryRequest));
        assertThat(resourceNotFoundException.getMessage(),is("SystemCategory is already existed. Please enter a different SystemCategory"));
    }

    @Test
    void updateSystemCategory_ShouldReturnObject_WhenDataValid(){
        when(systemCategoryRepository.findById(1L)).thenReturn(Optional.of(systemCategory));
        when(systemCategoryRepository.findByName("Test")).thenReturn(Optional.of(optionalSystemCategory));
        when(modelMapper.map(systemCategoryRequest,SystemCategory.class)).thenReturn(systemCategory);
        when(systemCategoryRepository.save(systemCategory)).thenReturn(systemCategory);
        when(modelMapper.map(systemCategory,SystemCategoryResponse.class)).thenReturn(systemCategoryResponse);
        SystemCategoryResponse result = systemCategoryService.updateNameCategory(systemCategoryRequest,1L);
        assertThat(result,is(systemCategoryResponse));
    }

    @Test
    void updateSystemCategory_ShouldReturnException_WhenNotFoundSystemCategory(){
        when(systemCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> systemCategoryService.updateNameCategory(systemCategoryRequest,1L));
        assertThat(resourceNotFoundException.getMessage(),is("Not found System Category"));
    }

    @Test
    void deleteSystemCategory_ShouldReturnObject_WhenDataValid(){
        when(systemCategoryRepository.findById(1L)).thenReturn(Optional.of(systemCategory));
        when(systemCategory.isStatus()).thenReturn(true);
        boolean result= systemCategoryService.deleteCategory(1L);
        assertThat(result,is(true));
    }
    @Test
    void deleteSystemCategory_ShouldReturnException_WhenStatusFalse(){
        when(systemCategoryRepository.findById(1L)).thenReturn(Optional.of(systemCategory));
        when(systemCategory.isStatus()).thenReturn(false);
        ForbiddenException forbiddenException = Assertions.assertThrows(ForbiddenException.class,
                () -> systemCategoryService.deleteCategory(1L));
        assertThat(forbiddenException.getMessage(),is("SystemCategory already disable"));
    }

    @Test
    void deleteSystemCategory_ShouldReturnException_WhenSystemCategoryEmpty(){
        when(systemCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> systemCategoryService.deleteCategory(1L));
        assertThat(resourceNotFoundException.getMessage(),is("SystemCategory not found"));
    }
    @Test
    void getAllHotel_ShouldReturnListHotelResponse_WhenDataValid(){
        when(systemCategoryRepository.getAllCategoryByStatus()).thenReturn(systemCategoryList);
        when(modelMapper.map(systemCategory,SystemCategoryResponse.class)).thenReturn(systemCategoryResponse);
        List<SystemCategoryResponse> hotelResponses= systemCategoryService.getAllCategory();
        assertThat(hotelResponses.size(),is(systemCategoryList.size()));
    }

    @Test
    void getAllHotel_ShouldReturnException_WhenHotelIsEmpty(){
        when(systemCategoryRepository.getAllCategoryByStatus()).thenReturn(systemCategoryList);
        when(systemCategoryList.isEmpty()).thenReturn(true);
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> systemCategoryService.getAllCategory());
        assertThat(resourceNotFoundException.getMessage(),is("SystemCategory not found"));
    }
}

