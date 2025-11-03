package com.wecp.medicalequipmentandtrackingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "Order ID cannot be null")
    private Long id;

    @NotNull(message = "Order date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Pending|Shipped|Delivered", message = "Status must be either Pending, Shipped, or Delivered")
    private String status;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    private String equipmentName;
    private String equipmentDescription;

    // Hospital details
    private Long hospitalId;
    private String hospitalName;
    private String hospitalLocation;

}