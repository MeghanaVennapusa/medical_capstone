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


public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    
    //call save method grom jps repository to save equipment object
    public Equipment createEquipment(Equipment equipment){
        Optional<Hospital> hosp = hospitalRepository.findById(equipment.getHospital().getId());
        if(hosp.isPresent()){
            equipment.setHospital(hosp.get());
            return equipmentRepository.save(equipment);

        }
        else{
            throw new RuntimeException("Hospital ID is not present");
        }

     
    }
     
    //call findAll method from the jpa repository
    public List<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }
}
