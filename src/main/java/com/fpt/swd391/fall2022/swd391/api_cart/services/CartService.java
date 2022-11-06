package com.fpt.swd391.fall2022.swd391.api_cart.services;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartResponse;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Cart;
import com.fpt.swd391.fall2022.swd391.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface CartService {

      Cart getCartByProduct(long idProduct, long idAccount);
      boolean isCartExistByProduct(long id, long userId );
      Collection<CartDto> getCartByIdAccount(Long idAccount);
      Account getAccountById(long idAccount);
      Product getProductById(long idProduct);

      Cart getById(long cartId);

      CartResponse addCart(CartDto cartDto);

      CartResponse updateCart(Long id,CartDto cartDto);

      ResponseEntity<?> deleteCart(Long idAccount);
      //-------------------------
//      List<Cart> listCart(Account account);

}
