package com.fpt.swd391.fall2022.swd391.api_order;

import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
import com.fpt.swd391.fall2022.swd391.api_order.details.OrderDetailRepository;
import com.fpt.swd391.fall2022.swd391.api_order.details.OrderDetailService;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDetailDTO;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDto;
import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderRequestDTO;
import com.fpt.swd391.fall2022.swd391.api_shop.ShopRepository;
import com.fpt.swd391.fall2022.swd391.entity.*;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {
    final  OrderRepository orderRepository;
    final OrderDetailRepository orderDetailRepository;
    final ModelMapper modelMapper;
    final CartService cartService;
    final ShopRepository shopRepository;
    final OrderDetailService orderDetailService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
                            ModelMapper modelMapper, CartService cartService, ShopRepository shopRepository, OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
        this.cartService = cartService;
        this.shopRepository = shopRepository;
        this.orderDetailService = orderDetailService;
    }


    @Override
    public OrderDto findByIdOrder(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("Order not found");
        }
        Order order = optional.get();
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<Order> list = orderRepository.findAll();
        return list.stream()
                   .map(order -> {
                       List<OrderDetailDTO> orderDetailDTOS = order.getOrderDetails().stream()
                                                                                     .map(orderDetailService::fromEntityToDTO)
                                                                                     .collect(Collectors.toList());
                      return  OrderDto.builder()
                                      .idAccount(order.getAccount().getId())
                                      .idShop(order.getShop().getId())
                                      .id(order.getId())
                                      .orderDetailList(orderDetailDTOS)
                                      .totalPrice(order.getTotalPrice()).build();
                                }).collect(Collectors.toList());
    }


    @Override
    public Order addOrder(OrderRequestDTO orderRequestDTO) {
        long idAccount = orderRequestDTO.getIdAccount();
        long idShop = orderRequestDTO.getIdShop();
        String location = orderRequestDTO.getLocation();

        Account account = cartService.getAccountById(idAccount);
        Shop shop = this.shopRepository.findById(idShop)
                                       .orElseThrow(
                                               () -> new ResourceNotFoundException("Not Found Shop By ID: " + idShop));
        Collection<Cart> carts = account.getCartItemCollection();
        Set<OrderDetail> orderDetails = carts.stream()
                                             .map(orderDetailService::addFromCartItem)
                                             .collect(Collectors.toSet());

        int totalPrice = orderDetails.stream()
                                     .map(OrderDetail::getPrice)
                                     .reduce(0, Integer::sum);

        Order order = Order.builder()
                           .account(account)
                           .shop(shop)
                           .orderDetails(orderDetails)
                           .location(location)
                           .status(true)
                           .totalPrice(totalPrice)
                           .build();

        Order savedOrder = this.orderRepository.save(order);

        savedOrder.getOrderDetails().forEach(
                item -> orderDetailService.updateWith(savedOrder, item)
        );

        return savedOrder;


    }
}
