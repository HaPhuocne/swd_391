package com.fpt.swd391.fall2022.swd391.api_cart.services;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CartService {
      Collection<CartDto> getCartByIdAccount(Long idAccount);

      ResponseEntity<?> addCart(CartDto cartDto);

      ResponseEntity<?> updateCart(Long id,CartDto cartDto);

      ResponseEntity<?> deleteCart(Long idAccount);}
