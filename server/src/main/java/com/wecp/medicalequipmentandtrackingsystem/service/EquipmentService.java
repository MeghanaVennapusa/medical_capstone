package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    //call save method grom jps repository to save equipment object
    public Equipment createEquipment(Equipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment object is null");
        }
    
        if (equipment.getHospital() == null || equipment.getHospital().getId() == null) {
            throw new IllegalArgumentException("Hospital information is missing in equipment");
        }
    
        // Proceed with saving
        return equipmentRepository.save(equipment);
    }
     
    //call findAll method from the jpa repository
    public List<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }
}
