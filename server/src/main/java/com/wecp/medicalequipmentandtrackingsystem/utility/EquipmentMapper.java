package com.wecp.medicalequipmentandtrackingsystem.utility;

import org.springframework.stereotype.Component;

import com.wecp.medicalequipmentandtrackingsystem.dto.EquipmentDTO;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;

@Component
public class EquipmentMapper {

    public Equipment mapToEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.getName());
        equipment.setDescription(equipmentDTO.getDescription());

        if (equipmentDTO.getHospitalId() != null) {
            Hospital hospital = new Hospital();
            hospital.setId(equipmentDTO.getHospitalId());
            equipment.setHospital(hospital);
        }

        return equipment;
    }
}
