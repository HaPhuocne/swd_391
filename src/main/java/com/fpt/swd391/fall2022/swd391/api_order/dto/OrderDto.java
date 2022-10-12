package com.fpt.swd391.fall2022.swd391.api_order.dto;

import com.fpt.swd391.fall2022.swd391.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private int totalPrice;
    private Long idAccount;
    private Long idShop;
    private List<OrderDetailDTO> orderDetailList;
}
