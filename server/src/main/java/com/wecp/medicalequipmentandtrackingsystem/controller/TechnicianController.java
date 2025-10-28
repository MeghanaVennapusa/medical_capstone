package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
public class TechnicianController {

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);

    // DI for MaintenanceService
    @Autowired
    MaintenanceService maintenanceService;

    // Constructor DI for the MaintenanceService
    public TechnicianController(MaintenanceService maintenanceService) {

        this.maintenanceService = maintenanceService;

    }

    // Get all maintenance records and return them with status code 200 OK;
    @GetMapping("/api/technician/maintenance")
    public ResponseEntity<?> getAllMaintenance() {
        try {
            List<Maintenance> maintenances = maintenanceService.getAllMaintenances();

            if (maintenances == null || maintenances.isEmpty()) {
                logger.warn("No maintenance records found.");
                return new ResponseEntity<>("No maintenance records available.", HttpStatus.NO_CONTENT);
            }

            logger.info("All the maintenances are retrieved successfully!");
            return new ResponseEntity<>(maintenances, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while retrieving maintenance records: {}", e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update the maintenance record with the given id and return updated record with status code 200 OK;
    @PutMapping("/api/technician/maintenance/update/{maintenanceId}")
    public ResponseEntity<?> updateMaintenance(@PathVariable Long maintenanceId,
                                           @RequestBody Maintenance updatedMaintenance) {

    try {

        if (updatedMaintenance == null) {
            logger.warn("maintenance to be updated cannot be null!");
            return ResponseEntity.badRequest().body("Updated maintenance data cannot be null.");
        }

        Optional<Maintenance> existingMaintenance = maintenanceService.findById(maintenanceId);
        if (existingMaintenance.isEmpty()) {
            logger.info("maintenance not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Maintenance record with ID " + maintenanceId + " not found.");
        }

        Maintenance maintenance = maintenanceService.updateMaintenance(maintenanceId, updatedMaintenance);
        logger.info("maintenance updated successfully!");
        return ResponseEntity.ok(maintenance);

    } catch (IllegalArgumentException e) {
        logger.error("Update Maintenance Invalid Form.");
        return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());

    } catch (Exception e) {

        logger.error("Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An unexpected error occurred: " + e.getMessage());

    }

}

}