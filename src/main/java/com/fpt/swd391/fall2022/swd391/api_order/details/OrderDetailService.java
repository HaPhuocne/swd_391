package com.fpt.swd391.fall2022.swd391.api_order.details;

import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDetailDTO;
import com.fpt.swd391.fall2022.swd391.entity.Cart;
import com.fpt.swd391.fall2022.swd391.entity.Order;
import com.fpt.swd391.fall2022.swd391.entity.OrderDetail;

public interface OrderDetailService {


    OrderDetail addFromCartItem(Cart cart);
    OrderDetail updateWith(Order order, OrderDetail orderDetail);
    OrderDetailDTO fromEntityToDTO(OrderDetail orderDetail);
}
