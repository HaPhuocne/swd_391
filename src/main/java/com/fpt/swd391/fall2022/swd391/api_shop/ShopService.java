package com.fpt.swd391.fall2022.swd391.api_shop;

import org.springframework.stereotype.Service;

@Service
public interface ShopService {
    ShopResponse addShop(Long idAccount,ShopRequest shopRequest);
    boolean deleteShop(Long id);
}
