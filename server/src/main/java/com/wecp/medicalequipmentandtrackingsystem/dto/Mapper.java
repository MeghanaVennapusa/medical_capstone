package com.wecp.medicalequipmentandtrackingsystem.dto;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;

public class Mapper {

    public static Maintenance mapToEntity(MaintenanceScheduleDTO dto, Equipment equipment) {
    Maintenance maintenance = new Maintenance();
    maintenance.setScheduledDate(dto.getScheduledDate());
    maintenance.setDescription(dto.getDescription());
    maintenance.setStatus(dto.getStatus());
    maintenance.setEquipment(equipment);
    return maintenance;
}

public static void updateEntityFromDto(MaintenanceUpdateDTO dto, Maintenance maintenance) {
    maintenance.setCompletedDate(dto.getCompletedDate());
    maintenance.setStatus(dto.getStatus());
    maintenance.setDescription(dto.getDescription());
}

public static User toEntity(UserDTO userDTO) {
    if (userDTO == null) {
        return null;
    }

    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    user.setEmail(userDTO.getEmail());
    user.setRole(userDTO.getRole());
    return user;
}

    
}
