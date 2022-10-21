package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest,Long idShop);
    ProductResponse updateProduct(ProductRequest productRequest,Long id);
    boolean deleteProduct(Long id);
    ProductPageResponse<ProductResponse> ListContentSearchProduct(String content, int pageNo, int pageSize);

    List<ProductResponse> getAllProduct();

    ResponseEntity<?> findById(Long id);

    List<ProductResponse> getProductByShopName(String shopName);
    List<ProductResponse> getProductBySystemCategoryName(String shopName);

}
