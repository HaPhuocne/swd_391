package com.fpt.swd391.fall2022.swd391.api_product;

import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.api_system_category.SystemCategoryRepository;
import com.fpt.swd391.fall2022.swd391.api_user.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import com.fpt.swd391.fall2022.swd391.exception.ForbiddenException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
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
                () -> new ResourceNotFoundException("Not found category")
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
    public List<ProductResponse> getAllProduct(){
        List<Product> productList = productRepository.getAllByStatus();
        if(productList.isEmpty()){
            throw new ResourceNotFoundException("No product");
        }
        return productList.stream().map(ProductResponse::buildFromProduct).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            if (optionalProduct.get().isStatus()) {
                return ResponseEntity.ok().body(new MessageResponse("Search successful", modelMapper.map(optionalProduct.get(), ProductResponse.class)));
            }
            throw new ResourceNotFoundException("Product found");
        }
        throw new ResourceNotFoundException("Product found");
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
