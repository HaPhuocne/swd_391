package com.fpt.swd391.fall2022.swd391.api_cart;

import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addCart(@RequestParam(required = false) Long idAccount,
                                     @RequestParam(required = false) Long idProduct,
                                     @RequestParam(required = false, defaultValue = "1") int quantity) {
        CartDto cartDto = new CartDto(quantity,idAccount,idProduct);
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
