package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.exceptions.ResourceNotFoundException;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    
    private EquipmentRepository equipmentRepository;
    private HospitalRepository hospitalRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, HospitalRepository hospitalRepository) {
        this.equipmentRepository = equipmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Equipment createEquipment(Long hospitalId, Equipment equipment) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital with ID " + hospitalId + " not found"));
        equipment.setHospital(hospital);
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getEquipmentsByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital with ID " + hospitalId + " not found"));
        return hospital.getEquipmentList();

    }


    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }



    public Equipment getEquipmentById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment with ID " + equipmentId + " does not exist"));
    }



    public List<Equipment> getEquipmentsByHospitalName(String hospitalName) {
        Hospital hospital = hospitalRepository.findByName(hospitalName);
        if (hospital == null) {
            throw new ResourceNotFoundException("Hospital with name '" + hospitalName + "' not found");
        }
        return hospital.getEquipmentList();
    }

}
