package com.fpt.swd391.fall2022.swd391.api_warehouse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseResponse {
    Long id;
    String location;
    int quantity;
}
