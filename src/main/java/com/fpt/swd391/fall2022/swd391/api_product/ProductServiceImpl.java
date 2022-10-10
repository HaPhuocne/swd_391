package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PageResponse;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    final
    ProductRepository productRepository;
    final
    ModelMapper modelMapper;
    final
    ShopRepository shopRepository;
    final
    SystemCategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ShopRepository shopRepository, SystemCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, Long idShop) {
        Product product = modelMapper.map(productRequest,Product.class);
        Shop shop = shopRepository.findById(idShop).orElseThrow(
                () -> new ResourceNotFoundException("Not found shop")
        );
        SystemCategory systemCategory = categoryRepository.findById(productRequest.getIdSystemCategory()).orElseThrow(
                ()-> new ResourceNotFoundException("Not found systemCategory")
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
        modelMapper.map(productRequest,oldProduct);
        oldProduct.setShop(oldProduct.getShop());
        oldProduct.setSystemCategory(systemCategory);
        oldProduct.setStatus(oldProduct.isStatus());

        Product saveProduct= productRepository.save(oldProduct);

        return ProductResponse.buildFromProduct(saveProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> productList = productRepository.getAllByStatus();
        if (productList.isEmpty()){
            throw new ResourceNotFoundException("No product");
        }
        return productList.stream().map(ProductResponse::buildFromProduct).collect(Collectors.toList());
    }
    @Override
    public List<ProductResponse> findProduct(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
//        List<SystemCategoryResponse> systemCategoryResponseList = new ArrayList<>();
//        productPage.forEach(h -> systemCategoryResponseList.add(modelMapper.map(h,SystemCategoryResponse.class)));
        return productPage.stream().map(ProductResponse::buildFromProduct).collect(Collectors.toList());
    }

    @Override
    public ProductPageResponse<ProductResponse> ListContentSearchProduct(String content, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Product> productPage = productRepository.searchProductBy(content,pageable);
        if (productPage.isEmpty()) {
            throw new ResourceNotFoundException("No Page");
        }
        return new ProductPageResponse<ProductResponse>()
                .setPageSize(productPage.getSize())
                .setTotalSize(productPage.getTotalElements())
                .setList(productPage
                        .stream()
                        .map(ProductResponse :: buildFromProduct)
                        .collect(Collectors.toList()))
                .setPageNo(productPage.getNumber())
                ;
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

    @Override
    public List<ProductResponse> searchProductBy(String content,int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Product> productList = productRepository.searchProductBy(content,pageable);
        if (productList.isEmpty()){
            throw new ResourceNotFoundException("No product");
        }
        return productList.stream().map(ProductResponse :: buildFromProduct).collect(Collectors.toList());
    }
}
