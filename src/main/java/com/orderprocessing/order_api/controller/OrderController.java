package com.orderprocessing.order_api.controller;

import com.orderprocessing.order_api.model.Order;
import com.orderprocessing.order_api.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "Endpoints for managing the O2C lifecycle")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint 1: POST http://localhost:8080/api/orders
    @PostMapping
    @Operation(summary = "Create a new order", description = "Validates total amount and initializes order as PENDING")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.createOrder(order);
            return ResponseEntity.ok(savedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint 2: GET http://localhost:8080/api/orders
    @GetMapping
    @Operation(summary = "Get all orders", description = "Shows all the orders along with their statuses")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Endpoint 3: GET http://localhost:8080/api/orders/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get specific order", description = "Show specific order based on the order ID")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint 4: PATCH http://localhost:8080/api/orders/{id}/status?newStatus=SHIPPED
    @PatchMapping("/{id}/status")
    @Operation(summary = "Update order status", description = "Update the status of the order to SHIPPED")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint 5: DELETE http://localhost:8080/api/orders/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the order", description = "Deletes the specific order based on the ID")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}