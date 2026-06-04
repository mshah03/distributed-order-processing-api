package com.orderprocessing.order_api.service;

import com.orderprocessing.order_api.model.Order;
import com.orderprocessing.order_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 1. Business Logic for Creating an Order
    public Order createOrder(Order order) {
        // Business Rule Validation
        if (order.getTotalAmount() == null || order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Order total must be greater than zero.");
        }
        
        // System Automation: Automatically set the initial lifecycle state
        order.setOrderStatus("PENDING");
        
        // Save using the repository layer
        return orderRepository.save(order);
    }

    // 2. Business Logic for Fetching all Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 3. Fetch a specific order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
    }

    // 4. Update the Order Status (O2C Lifecycle)
    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order existingOrder = getOrderById(orderId);

        // Prevent modifying cancelled orders
        if ("CANCELLED".equalsIgnoreCase(existingOrder.getOrderStatus())) {
            throw new IllegalStateException("Cannot update a cancelled order.");
        }

        // System Automation: Convert to uppercase for database consistency (e.g., "BOOKED", "SHIPPED")
        existingOrder.setOrderStatus(newStatus.toUpperCase());
        
        return orderRepository.save(existingOrder);
    }

    // 5. Cancel/Delete an order
    public void deleteOrder(Long orderId) {
        Order existingOrder = getOrderById(orderId);
        orderRepository.delete(existingOrder);
    }
}