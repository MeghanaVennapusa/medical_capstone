package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    public Equipment createEquipment(Equipment equipment){
     return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }
}
