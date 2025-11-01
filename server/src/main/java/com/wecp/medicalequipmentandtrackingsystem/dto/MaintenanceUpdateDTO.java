package com.wecp.medicalequipmentandtrackingsystem.dto;

import lombok.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceUpdateDTO {

    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date completedDate;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
}