package com.wecp.medicalequipmentandtrackingsystem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EquipmentDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(max = 200, message = "Description can be up to 200 characters")
    private String description;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;

    public void setHospital(Hospital hsptl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHospital'");
    }

    
}