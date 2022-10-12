package com.fpt.swd391.fall2022.swd391.api_cart;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
	public ResponseEntity<?> getCartByIdAccount(@PathVariable("id") Long id){
		return ResponseEntity.ok(cartService.getCartByIdAccount(id));
	}
    @PostMapping("/")
    public ResponseEntity<?> addCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok(cartService.addCart(cartDto));
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> updateCart(@PathVariable("id") Long id, CartDto cartDto) {
        return ResponseEntity.ok(cartService.updateCart(id, cartDto));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        return cartService.deleteCart(id);
    }
}
