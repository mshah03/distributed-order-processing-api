package com.orderprocessing.order_api.repository;

import com.orderprocessing.order_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // You don't need to write ANY code in here for basic CRUD!
    
}