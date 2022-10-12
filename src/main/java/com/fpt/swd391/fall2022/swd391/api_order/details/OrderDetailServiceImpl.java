package com.fpt.swd391.fall2022.swd391.api_order.details;

import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDetailDTO;
import com.fpt.swd391.fall2022.swd391.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ModelMapper modelMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDetail addFromCartItem(Cart cart) {
        Product product = cart.getProduct();
        int quantity = cart.getQuantity();

        OrderDetail orderDetail = OrderDetail.builder()
                                             .product(product)
                                             .quantity(quantity)
                                             .nameProduct(product.getName())
                                             .image(product.getImage())
                                             .price(product.getPrice() * cart.getQuantity())
                                             .build();

        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail updateWith(Order order, OrderDetail orderDetail) {
        orderDetail.setOrder(order);
        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetailDTO fromEntityToDTO(OrderDetail orderDetail) {
        return modelMapper.map(orderDetail, OrderDetailDTO.class);
    }
}
