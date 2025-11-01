package com.wecp.medicalequipmentandtrackingsystem.dto;

import lombok.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceScheduleDTO {

    @NotNull(message = "Scheduled date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date scheduledDate;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
}