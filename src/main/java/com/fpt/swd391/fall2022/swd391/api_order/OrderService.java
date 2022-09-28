package com.fpt.swd391.fall2022.swd391.api_order;

import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
      OrderDto findByIdOrder(long id);
      ResponseEntity<?> getAllOrder();
      ResponseEntity<?> addOrder(OrderDto dto);
}
