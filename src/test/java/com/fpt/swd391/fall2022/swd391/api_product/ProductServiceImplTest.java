package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryResponse;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductServiceImplTest {
    ProductService productService;

    ProductRepository productRepository;
    ModelMapper modelMapper;
    ShopRepository shopRepository;
    SystemCategoryRepository systemCategoryRepository;

    Product product;
    Product optionalProduct;
    Product expectedProduct;

    ProductRequest productRequest;
    ProductResponse productResponse;

    List<Product> productList;
}
