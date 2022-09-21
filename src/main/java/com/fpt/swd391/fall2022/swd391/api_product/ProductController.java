package com.fpt.swd391.fall2022.swd391.api_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    ProductResponse addProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long idShop){
        return productService.createProduct(productRequest,idShop);
    }

    @PutMapping
    ProductResponse updateProduct(@Valid @RequestBody ProductRequest productRequest,@PathVariable Long id){
        return productService.updateProduct(productRequest,id);
    }

    @GetMapping
    List<ProductResponse> getAll(){
        return productService.getAllProduct();
    }

    @DeleteMapping
    ResponseEntity<?> deleteProduct(@PathVariable Long id){
        if(productService.deleteProduct(id)){
            return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.OK);
        }
        return new ResponseEntity<>("DELETE SUCCESSFULLY", null, HttpStatus.BAD_REQUEST);
    }
}
