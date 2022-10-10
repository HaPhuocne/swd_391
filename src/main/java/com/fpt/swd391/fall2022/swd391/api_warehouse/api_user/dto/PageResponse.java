package com.fpt.swd391.fall2022.swd391.api_warehouse.api_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int pageNumber;
    private int pageSize;
    private Long totalSize;
    private List<T> list;
}
