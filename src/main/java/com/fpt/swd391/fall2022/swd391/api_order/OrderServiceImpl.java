//package com.fpt.swd391.fall2022.swd391.api_order;
//
//import com.fpt.swd391.fall2022.swd391.api_cart.dto.CartDto;
//import com.fpt.swd391.fall2022.swd391.api_cart.services.CartService;
//import com.fpt.swd391.fall2022.swd391.api_order.dto.OrderDto;
//import com.fpt.swd391.fall2022.swd391.api_role.dto.MessageResponse;
//import com.fpt.swd391.fall2022.swd391.entity.Order;
//import com.fpt.swd391.fall2022.swd391.entity.OrderDetail;
//import com.fpt.swd391.fall2022.swd391.entity.Product;
//import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//@Component
//public class OrderServiceImpl implements OrderService {
//    final  OrderRepository orderRepository;
//    final  OrderDetailRepository orderDetailRepository;
//    final ModelMapper modelMapper;
//    final CartService cartService;
//
//    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ModelMapper modelMapper, CartService cartService) {
//        this.orderRepository = orderRepository;
//        this.orderDetailRepository = orderDetailRepository;
//        this.modelMapper = modelMapper;
//        this.cartService = cartService;
//    }
//
//    @Override
//    public OrderDto findByIdOrder(long id) {
//        Optional<Order> optional = orderRepository.findById(id);
//        if (!optional.isPresent()) {
//            throw new ResourceNotFoundException("Order not found");
//        }
//        Order order = optional.get();
//        return modelMapper.map(order, OrderDto.class);
//    }
//
//    @Override
//    public ResponseEntity<?> getAllOrder() {
//        List<Order> list = orderRepository.findAll();
//        List<OrderDto> listDto = new ArrayList<>();
//        list.forEach(s -> listDto.add(modelMapper.map(s, OrderDto.class)));
//
//        return ResponseEntity.ok(listDto);
//    }
//
//    @Override
//    public ResponseEntity<?> addOrder(OrderDto ordersDto) {
//        Collection<CartDto> cartItems = cartService.getCartByIdAccount(ordersDto.getIdAccount());
//        if (cartItems.isEmpty()) {
//            throw new ResourceNotFoundException("The order was not added successfully");
//        }
//        Order order = orderRepository.save(modelMapper.map(ordersDto, Order.class));
//        List<OrderDetail> listOrderDetails = new ArrayList<>();
//        for (CartDto cart : cartItems) {
//            OrderDetail orderdetail = new OrderDetail();
//            orderdetail.setQuantity(cart.getQuantity());
//            orderdetail.setProduct(modelMapper.map(cart.getIdProduct(),Product.class));
//            orderdetail.setOrder(order);
//            listOrderDetails.add(orderdetail);
////		OrderdetailsDto orderDetail = new OrderdetailsDto();
////		orderDetail.setQuantity(cart.getQuantity());
////		orderDetail.setOrder(ordersDto);
////		orderDetail.setProduct(productServices.findByIdProduct(cart.getProduct().getIdProduct()));
////		listOrderDetails.add(modelMapper.map(orderDetail, Orderdetails.class));
//        }
//        orderDetailRepository.saveAll(listOrderDetails);
//        cartService.deleteCart(ordersDto.getIdAccount());
//        return ResponseEntity.ok(new MessageResponse("The order was added successfully",ordersDto));
//    }
//}
