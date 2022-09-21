package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryRepository;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    SystemCategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, Long idShop) {
        Product product = modelMapper.map(productRequest,Product.class);
        Shop shop = shopRepository.findById(idShop).orElseThrow(
                () -> new NotFoundException("Not found shop")
        );
        SystemCategory systemCategory = categoryRepository.findById(productRequest.getIdSystemCategory()).orElseThrow(
                ()-> new NotFoundException("Not found systemCategory")
        );
        product.setShop(shop);
        product.setSystemCategory(systemCategory);
        product.setStatus(true);

        Product saveProduct= productRepository.save(product);

        return ProductResponse.buildFromProduct(saveProduct);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        Product oldProduct= productRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Not found Product")
        );
        if(!oldProduct.isStatus()){
            throw new ForbiddenException("Product already disable");
        }
        SystemCategory systemCategory = categoryRepository.findById(productRequest.getIdSystemCategory()).orElseThrow(
                ()-> new NotFoundException("Not found systemCategory")
        );
        Product product = modelMapper.map(oldProduct,Product.class);
        product.setShop(oldProduct.getShop());
        product.setSystemCategory(systemCategory);
        product.setStatus(oldProduct.isStatus());

        Product saveProduct= productRepository.save(product);

        return ProductResponse.buildFromProduct(saveProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> productList = productRepository.getAllByStatus();
        if (productList.isEmpty()){
            throw new ForbiddenException("No product");
        }
        List<ProductResponse> productResponseList = new ArrayList<>();
        productResponseList.forEach(
                p -> productResponseList.add(modelMapper.map(p,ProductResponse.class))
        );
        return productResponseList;
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found product")
        );
        if(!product.isStatus()){
            throw new ForbiddenException("Product already disable");
        }
        product.setStatus(false);
        productRepository.save(product);
        return true;
    }
}
