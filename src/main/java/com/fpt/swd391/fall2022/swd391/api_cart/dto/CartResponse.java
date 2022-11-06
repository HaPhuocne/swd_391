package com.fpt.swd391.fall2022.swd391.api_cart.dto;

import com.fpt.swd391.fall2022.swd391.api_product.ProductResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private ProductResponse productResponse;
    private InformationUserDtoResponse userDtoResponse;
    private int quantity;
}
