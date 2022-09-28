package com.fpt.swd391.fall2022.swd391.api_order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long idOrder;

    private String orderDate;

    private Double total;

//	private Collection<Orde rdetailsDto> orderdetailsCollection;

    private Long IdAccount;
    private Long IdShop;


}
