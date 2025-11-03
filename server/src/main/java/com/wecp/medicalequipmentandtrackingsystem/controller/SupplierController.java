package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.dto.OrderDTO;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.service.OrderService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@Slf4j
public class SupplierController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    private final OrderService orderService;

    public SupplierController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/supplier/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            log.info("Fetching all orders");
            List<Order> orders = orderService.getAllOrders();
            List<OrderDTO> orderDTOs = new ArrayList<>();

            for (Order order : orders) {
                orderDTOs.add(orderService.convertToDto(order));
            }

            log.debug("Orders fetched: {}", orderDTOs);
            return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching orders", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/supplier/order/update/{orderId}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId,@Valid @RequestBody OrderDTO orderDTO) {
        try {
            log.info("Updating order ID {} with status '{}'", orderId, orderDTO.getStatus());

            if (!orderId.equals(orderDTO.getId())) {
                log.warn("Path variable ID and DTO ID do not match");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Equipment equipment = equipmentRepository.findById(orderDTO.getEquipmentId())
                    .orElseThrow(() -> new RuntimeException("Equipment not found"));

            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setOrderDate(orderDTO.getOrderDate());
            order.setStatus(orderDTO.getStatus());
            order.setQuantity(orderDTO.getQuantity());
            order.setEquipment(equipment);

            Order updatedOrder = orderService.updateOrder(orderDTO.getId(), orderDTO.getStatus());
            OrderDTO updatedDto = orderService.convertToDto(updatedOrder);

            log.debug("Updated order: {}", updatedDto);
            return new ResponseEntity<>(updatedDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn("Error updating order ID {}: {}", orderId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
