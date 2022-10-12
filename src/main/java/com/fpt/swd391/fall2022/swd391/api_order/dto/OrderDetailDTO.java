package com.fpt.swd391.fall2022.swd391.api_order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long id;
    private String nameProduct;
    private int price;
    private int quantity;
    private String image;
}
