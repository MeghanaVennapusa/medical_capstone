package com.wecp.medicalequipmentandtrackingsystem.service;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    MaintenanceRepository maintenanceRepository;
    @Autowired
    EquipmentRepository equipmentRepository;

    // Constructor Injection for the repository
    public MaintenanceService(MaintenanceRepository maintenanceRepository){
        this.maintenanceRepository = maintenanceRepository;
    }

    // finds all the maintenances using the in-built method of JpaRepository (done by technician) 
    public List<Maintenance> getAllMaintenances(){
        return maintenanceRepository.findAll();
    }

    //update's the maintenance using maintenanceId (done by technician)
    public Maintenance updateMaintenance(long maintenanceId , Maintenance updatedMaintenance){
        Maintenance existingMaintenance = maintenanceRepository.findById(maintenanceId).get();

        if(existingMaintenance != null){
            
            updatedMaintenance.setId(existingMaintenance.getId());

            return maintenanceRepository.save(updatedMaintenance);
        }else{
            throw new RuntimeException("Maintenance not found!");
        }
    }

    public Maintenance createMaintenance(Long equipmentId, Maintenance maintenance)
    {
        maintenance.setEquipment(equipmentRepository.findById(equipmentId).get());
        return maintenanceRepository.save(maintenance);
    }

    
}