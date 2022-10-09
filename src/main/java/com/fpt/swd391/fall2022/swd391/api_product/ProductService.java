package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest,Long idShop);
    ProductResponse updateProduct(ProductRequest productRequest,Long id);
    List<ProductResponse> getAllProduct();
    boolean deleteProduct(Long id);
    List<ProductResponse> searchProductBy(String content,int pageNo, int pageSize);
    List<ProductResponse> findProduct(int pageNo, int pageSize);

}
