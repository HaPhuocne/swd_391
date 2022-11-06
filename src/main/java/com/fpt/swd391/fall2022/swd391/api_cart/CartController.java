package com.fpt.swd391.fall2022.swd391.api_cart;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart Controller", description = "API liên quan đến Cart")
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
    //-------------------------------
//    @GetMapping()
//    public String showCart(Model model, @AuthenticationPrincipal AuthenticationPrincipal authenticationPrincipal){
//        Account account =
//        return null;
//    }
}
