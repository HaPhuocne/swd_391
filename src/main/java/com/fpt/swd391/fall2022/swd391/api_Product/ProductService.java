package com.fpt.swd391.fall2022.swd391.api_Product;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest,Long idShop);
    ProductResponse updateProduct(ProductRequest productRequest,Long id);
    List<ProductResponse> getAllProduct();
    boolean deleteProduct(Long id);
}
