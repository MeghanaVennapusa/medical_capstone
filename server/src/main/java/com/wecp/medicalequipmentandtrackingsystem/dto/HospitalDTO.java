package com.wecp.medicalequipmentandtrackingsystem.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {

    private Long id;

    @NotBlank(message = "Hospital name is required")
    @Size(min = 2, max = 100, message = "Hospital name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    private String location;

    private List<Long> equipmentIds;
}