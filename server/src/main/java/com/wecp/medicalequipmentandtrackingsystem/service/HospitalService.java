package com.wecp.medicalequipmentandtrackingsystem.service;


import java.util.List;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Hospital> getAllHospitals() throws Exception{
        return hospitalRepository.findAll();
    }

    public Hospital addHospital(Hospital hospital) throws Exception{
        Hospital existingHospital=hospitalRepository.findByNameAndLocation(hospital.getName(), hospital.getLocation());
        if(existingHospital != null){
            throw new RuntimeException("Hospital already with same name and location");
        }
        return hospitalRepository.save(hospital);
    }

    public Hospital getHospitalById(Long id) throws Exception{
        return hospitalRepository.findById(id).get();
    }

    public Equipment addEquipment(Long id , Equipment equipment){

        Hospital hsptl = hospitalRepository.findById(id).get();
        List<Equipment> ls = hsptl.getEquipmentList();
        ls.add(equipment);
        equipment.setHospital(hsptl);
        hsptl.setEquipmentList(ls);
        hospitalRepository.save(hsptl);
        return equipmentRepository.save(equipment);

    }
    
}
