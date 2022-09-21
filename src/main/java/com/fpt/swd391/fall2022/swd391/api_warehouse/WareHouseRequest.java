package com.fpt.swd391.fall2022.swd391.api_warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseRequest {
    String Location;
    int quantity;
}
