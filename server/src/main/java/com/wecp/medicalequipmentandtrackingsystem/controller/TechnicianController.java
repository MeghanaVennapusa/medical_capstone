package com.wecp.medicalequipmentandtrackingsystem.controller;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TechnicianController {

    //DI for MaintenanceService
    @Autowired
    MaintenanceService maintenanceService;

    //Constructor DI for the MaintenanceService
    public TechnicianController(MaintenanceService maintenanceService){
        this.maintenanceService = maintenanceService;
    }

    // Get all maintenance records and return them with status code 200 OK;
    @GetMapping("/api/technician/maintenance")
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        return new ResponseEntity<List<Maintenance>>(this.maintenanceService.getAllMaintenances(), HttpStatus.OK);
    }


    // Update the maintenance record with the given id and return updated record with status code 200 OK;
    @PutMapping("/api/technician/maintenance/update/{maintenanceId}")
    public ResponseEntity<Maintenance> updateMaintenance
            (@PathVariable Long maintenanceId, @RequestBody Maintenance updatedMaintenance) {
                Maintenance maintenance = this.maintenanceService.updateMaintenance(maintenanceId, updatedMaintenance);
                return new ResponseEntity<Maintenance>(maintenance , HttpStatus.OK);
            }
}