package com.fpt.swd391.fall2022.swd391.api_cart.services.serviceImpl;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartResponse;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartAccountRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartProductRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.repositories.CartShopRepository;
import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import com.fpt.swd391.fall2022.swd391.api_product.ProductResponse;
import com.fpt.swd391.fall2022.swd391.api_role.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoResponse;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Cart;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.exception.ConstraintViolateException;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Account getAccountById(long idAccount) {
        return accountRepository.findById(idAccount)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found Account With ID: " + idAccount));
    }

    @Override
    public Product getProductById(long idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found Product With ID: " + idProduct));
    }

    @Override
    public Cart getCartByProduct(long idProduct, long idAccount) {
        Account account = getAccountById(idAccount);
        Product product = getProductById(idProduct);
        return this.cartRepository.findByAccountAndProduct(account, product);
    }

    @Override
    public boolean isCartExistByProduct(long id, long userId) {
        Account account = getAccountById(userId);
        Product product = getProductById(id);

        return cartRepository.existsByAccountAndProduct(account, product);
    }

    @Override
    public Collection<CartResponse> getCartByIdAccount(Long idAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);
        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        List<Cart> list = accountRepository.findCartByIdAccount(idAccount);
        return list.stream()
                .map(cart -> {
                    Account account = cart.getAccount();
                    Product product = cart.getProduct();
                    return CartResponse.builder()
                            .userDtoResponse(modelMapper.map(account, InformationUserDtoResponse.class))
                            .productResponse(modelMapper.map(product,ProductResponse.class))
                            .quantity(cart.getQuantity()).build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public Cart getById(long cartId) {
        return this.cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Not Found With ID: " + cartId));
    }


    @Override
    public CartResponse addCart(CartDto cartDto) {
        long idAccount = cartDto.getIdAccount();
        long idProduct = cartDto.getIdProduct();
        int quantity = cartDto.getQuantity();
        Product product = getProductById(idProduct);
        Account account = getAccountById(idAccount);

        if (isCartExistByProduct(idProduct, idAccount)) {
            throw new ConstraintViolateException("Cannot add to cart due to already exist !!!");
        }
        if (quantity > product.getQuantity()) {
            throw new ConstraintViolateException("Cannot add to cart due to over quantity !!!");
        }

        Cart cart = Cart.builder()
                        .account(account)
                        .product(product)
                        .quantity(quantity).build();
        cartRepository.save(cart);
        CartResponse cartResponse = new CartResponse();
        ProductResponse productResponse = modelMapper.map(product,ProductResponse.class);
        InformationUserDtoResponse userDtoResponse = modelMapper.map(account, InformationUserDtoResponse.class);
        cartResponse.setUserDtoResponse(userDtoResponse);
        cartResponse.setProductResponse(productResponse);
        cartResponse.setQuantity(cart.getQuantity());

        return cartResponse;
    }

    @Override
    public CartResponse updateCart(Long id, CartDto cartDto) {
        int quantity = cartDto.getQuantity();
//        long idProduct = cartDto.getIdProduct();
        Cart cart = this.cartRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found Cart With ID: " + id));
        int oldQuantity = cart.getQuantity();
        cart.setQuantity(oldQuantity + quantity);
        Product product = getProductById(cart.getProduct().getId());

        if (cart.getQuantity() > product.getQuantity()) {
            throw new ConstraintViolateException("Cannot add to cart due to over quantity !!!");
        }
        cartRepository.save(cart);
        CartResponse cartResponse = new CartResponse();
        ProductResponse productResponse = modelMapper.map(cart.getProduct(),ProductResponse.class);
        InformationUserDtoResponse userDtoResponse = modelMapper.map(cart.getAccount(), InformationUserDtoResponse.class);
        cartResponse.setUserDtoResponse(userDtoResponse);
        cartResponse.setProductResponse(productResponse);
        cartResponse.setQuantity(cart.getQuantity());

        return cartResponse;
    }

    @Override
    public ResponseEntity<?> deleteCart(Long idAccount) {
        List<Cart> list = cartRepository.findByIdAccount(idAccount);
        cartRepository.deleteAll(list);
        return ResponseEntity.ok(new MessageResponse("Deleted successfully", null));
    }

//    @Override
//    public List<Cart> listCart(Account account) {
//        return cartRepository.findByAccount(account);
//    }
}