package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import com.fpt.swd391.fall2022.swd391.api_warehouse.WareHouseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryNoteResponse {
    Date inputDate;
    Date outputDate;
    String shopName;
    WareHouseResponse wareHouseResponse;
}
