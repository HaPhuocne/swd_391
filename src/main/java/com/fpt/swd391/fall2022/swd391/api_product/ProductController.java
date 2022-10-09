package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/{idShop}")
    ProductResponse addProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long idShop){
        return productService.createProduct(productRequest,idShop);
    }

    @PutMapping("/{id}")
    ProductResponse updateProduct(@Valid @RequestBody ProductRequest productRequest,@PathVariable Long id){
        return productService.updateProduct(productRequest,id);
    }

    @GetMapping
    List<ProductResponse> getAll(){
        return productService.getAllProduct();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id){
        if(productService.deleteProduct(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{content}/{pageNo}/{pageSize}")
    List<ProductResponse> searchProductByContent(@PathVariable String content,@PathVariable int pageNo, @PathVariable int pageSize){
        return productService.searchProductBy(content,pageNo,pageSize);
    }
    @GetMapping("/{pageNo}/{pageSize}")
    List<ProductResponse> getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
        return productService.findProduct(pageNo,pageSize);
    }
}
