package com.fpt.swd391.fall2022.swd391.api_order;

import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDto;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    final
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @PostMapping("/")
    public ResponseEntity<?> addOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(orderService.addOrder(orderRequestDTO));
    }

}

