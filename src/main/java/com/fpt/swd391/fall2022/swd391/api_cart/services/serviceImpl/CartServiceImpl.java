package com.fpt.swd391.fall2022.swd391.api_cart.services.serviceImpl;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartAccountRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartProductRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartShopRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import com.fpt.swd391.fall2022.swd391.api_role.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Cart;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CartServiceImpl implements CartService {
    final CartAccountRepository accountRepository;
    final ModelMapper modelMapper;
    final CartProductRepository productRepository;
    final CartRepository cartRepository;
    final CartShopRepository cartShopRepository;


    public CartServiceImpl(CartAccountRepository accountRepository, ModelMapper modelMapper, CartProductRepository productRepository, CartRepository cartRepository, CartShopRepository cartShopRepository) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartShopRepository = cartShopRepository;
    }

    @Override
    public Collection<CartDto> getCartByIdAccount(Long idAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);
        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        List<Cart> list = accountRepository.findCartByIdAccount(idAccount);
        List<CartDto> dto = new ArrayList<>();
        list.forEach(c -> dto.add(modelMapper.map(c, CartDto.class)));
        return dto;
    }

    @Override
    public ResponseEntity<?> addCart(CartDto cartDto) {
        // TODO Auto-generated method stub
        Optional<Product> optionalProduct = productRepository.findById(cartDto.getIdProduct());
        if (!optionalProduct.isPresent()) {
            throw new ResourceNotFoundException("Product not found");
        }
        Optional<Account> optionalAccount = accountRepository.findById(cartDto.getIdAccount());
        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        Optional<Shop> optionalShop = cartShopRepository.findById(optionalAccount.get().getShop().getId());
        if(optionalShop.isPresent()){
          updateCart(optionalAccount.get().getId(),cartDto);
        }
        cartRepository.save(modelMapper.map(cartDto, Cart.class));
        return ResponseEntity.ok(new MessageResponse("Cart is added successfully",cartDto));

    }

    @Override
    public ResponseEntity<?> updateCart(Long id, CartDto cartDto) {
        Optional<Product> optionalProduct = productRepository.findById(cartDto.getIdProduct());
        if (!optionalProduct.isPresent()) {
            throw new ResourceNotFoundException("Product not found");
        }
        Optional<Account> optionalAccount = accountRepository.findById(cartDto.getIdAccount());
        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            if (cart.getQuantity() == 0) {
                cartRepository.delete(cart);
            }
            cart.setQuantity(cartDto.getQuantity());
            cartRepository.save(cart);
            return ResponseEntity.ok(new MessageResponse("Cart is updated successfully",cartDto));
        }
        throw new ResourceNotFoundException("Cart not found");
    }

    @Override
    public ResponseEntity<?> deleteCart(Long idAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);
        if(!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        List<Cart> list = cartRepository.findByIdAccount(idAccount);
        cartRepository.deleteAll(list);
        return ResponseEntity.ok(new MessageResponse("Deleted successfully",null));
    }
}