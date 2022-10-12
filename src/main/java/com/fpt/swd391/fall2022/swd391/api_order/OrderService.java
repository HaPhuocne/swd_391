package com.fpt.swd391.fall2022.swd391.api_order;

import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDto;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderRequestDTO;
import com.fpt.swd391.fall2022.swd391.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
      OrderDto findByIdOrder(long id);
      List<OrderDto> getAllOrder();
      Order addOrder(OrderRequestDTO orderRequestDTO);
}
