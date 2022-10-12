package com.fpt.swd391.fall2022.swd391.api_order.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequestDTO {

    private long idAccount;
    private String location;
    private long idShop;
}
