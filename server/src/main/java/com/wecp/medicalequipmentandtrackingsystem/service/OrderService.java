package com.wecp.medicalequipmentandtrackingsystem.service;

import com.wecp.medicalequipmentandtrackingsystem.dto.OrderDTO;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.OrderRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private UserRepository userRepository;
    // Constructor DI
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // provides the list of orders (done by supplier)
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    // update's the Order using OrderId (done by supplier)
    public Order updateOrder(Long orderId, String newStatus) {

        Order existingOrder = orderRepository.findById(orderId).get();

        if (existingOrder != null) {
            existingOrder.setStatus(newStatus);
            return orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found!");
        }
    }

    public Order createOrder(Long equipmentId, Order order) {
        order.setEquipment(equipmentRepository.findById(equipmentId).get());

        return orderRepository.save(order);
    }

    public OrderDTO convertToDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setQuantity(order.getQuantity());
        dto.setEquipmentId(order.getEquipment().getId());

        Equipment equipment = order.getEquipment();
        if (equipment != null) {
            dto.setEquipmentName(equipment.getName());
            dto.setEquipmentDescription(equipment.getDescription());

            Hospital hospital = equipment.getHospital();
            if (hospital != null) {
                dto.setHospitalId(hospital.getId());
                dto.setHospitalName(hospital.getName());
                dto.setHospitalLocation(hospital.getLocation());
            }
        }

        return dto;
    }
    public Integer getAllSuppliers()
    {
        Integer count=0;
        List<User> users = userRepository.findAll();

            for (User user : users) {
                if("SUPPLIER".equals(user.getRole()))
                    count++;
            }
            return count;
    }

}
