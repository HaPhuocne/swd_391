package com.fpt.swd391.fall2022.swd391.api_shop;

import com.fpt.swd391.fall2022.swd391.api_product.ProductRepository;
import com.fpt.swd391.fall2022.swd391.api_user.UserRepository;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService{
    final
    ShopRepository shopRepository;
    final
    UserRepository userRepository;
    final
    ModelMapper modelMapper;

    final
    ProductRepository productRepository;

    public ShopServiceImpl(ShopRepository shopRepository, UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ShopResponse addShop(Long idAccount, ShopRequest shopRequest) {
        Shop shop = modelMapper.map(shopRequest,Shop.class);
        Account account = userRepository.findById(idAccount).orElseThrow(
                () -> new ResourceNotFoundException("Not found account")
        );
        shop.setAccount(account);
        shop.setStatus(true);
        shopRepository.save(shop);
        return modelMapper.map(shop,ShopResponse.class);
    }

    @Override
    public boolean deleteShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found shop")
        );
        shop.setStatus(false);
        shopRepository.save(shop);
        return true;
    }

}
