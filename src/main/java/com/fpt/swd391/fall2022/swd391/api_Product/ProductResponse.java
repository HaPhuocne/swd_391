package com.fpt.swd391.fall2022.swd391.api_Product;

import com.fpt.swd391.fall2022.swd391.entity.Product;
import com.fpt.swd391.fall2022.swd391.entity.Shop;
import com.fpt.swd391.fall2022.swd391.entity.SystemCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    Long id;
    String name;
    int price;
    int quantity;
    String systemCategoryName;

    public static ProductResponse buildFromProduct(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getSystemCategory().getName()
        );
    }
}
