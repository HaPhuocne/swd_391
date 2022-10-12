package com.fpt.swd391.fall2022.swd391.api_order;

import com.fpt.swd391.fall2022.swd391.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    @Query("SELECT od.orderDetails FROM Order od where od.orderDetails= :idOrder")
//    List<OrderDetail> findAllyIdOrder(Long idOrder);

     Optional<Order> findById(Long id);

     @Query("select o from Order o")
     List<Order> findAll();
}
