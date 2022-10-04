package com.fpt.swd391.fall2022.swd391.api_product;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest,Long idShop);
    ProductResponse updateProduct(ProductRequest productRequest,Long id);
    List<ProductResponse> getAllProduct();
    ProductResponse getProductBy(String id);
    boolean deleteProduct(Long id);
}
