package com.wecp.medicalequipmentandtrackingsystem.repository;


import com.wecp.medicalequipmentandtrackingsystem.dto.EquipmentDTO;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    // extent jpa repository and add custom methods if needed
}
