package com.wecp.medicalequipmentandtrackingsystem.repository;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// extend jpa repository and add method if needed
@Repository
public interface OrderRepository extends JpaRepository<Order , Long>{   
}
