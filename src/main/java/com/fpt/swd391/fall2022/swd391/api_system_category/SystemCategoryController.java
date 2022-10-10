package com.fpt.swd391.fall2022.swd391.api_system_category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/system-categories")
public class SystemCategoryController {
    final
    SystemCategoryService systemCategoryService;

    public SystemCategoryController(SystemCategoryService systemCategoryService) {
        this.systemCategoryService = systemCategoryService;
    }

    @PostMapping
    SystemCategoryResponse createSystemCategory(@Valid @RequestBody SystemCategoryRequest systemCategoryRequest){
        return systemCategoryService.createCategory(systemCategoryRequest);
    }

    @PutMapping("/{id}")
    SystemCategoryResponse updateNameSystemCategory(@Valid @RequestBody SystemCategoryRequest systemCategoryRequest,@PathVariable Long id){
        return systemCategoryService.updateNameCategory(systemCategoryRequest,id);
    }

    @GetMapping
    List<SystemCategoryResponse> getAll(){
        return systemCategoryService.getAllCategory();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id){
        if (systemCategoryService.deleteCategory(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{pageNo}/{pageSize}")
    List<SystemCategoryResponse> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
        return systemCategoryService.findSystemCategories(pageNo,pageSize);
    }

}
