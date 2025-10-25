package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    //Constructor DI
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    //provides the list of orders (done by supplier) 
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    //update's the Order using OrderId (done by supplier)
    public Order updateOrder(Long orderId , String newStatus){

        Order existingOrder = orderRepository.findById(orderId).get();

        if(existingOrder != null){
            existingOrder.setStatus(newStatus);
            return orderRepository.save(existingOrder);
        }else{
            throw new RuntimeException("Order not found!");
        }

        
    }


}
