package com.fpt.swd391.fall2022.swd391.api_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageResponse<T> {
    private int pageNo;
    private int pageSize;
    private Long totalSize;
    private List<T> list;
}
