package com.fpt.swd391.fall2022.swd391.api_system_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/SystemCategory")
public class SystemCatrgoryController {
    @Autowired
    SystemCategoryService systemCategoryService;

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
    ResponseEntity<?> deleteCategory(Long id){
        if (systemCategoryService.deleteCategory(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE FAIL",null,HttpStatus.BAD_REQUEST);
    }

}
