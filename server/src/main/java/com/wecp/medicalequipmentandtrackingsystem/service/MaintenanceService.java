package com.wecp.medicalequipmentandtrackingsystem.service;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.exceptions.ResourceNotFoundException;
import com.wecp.medicalequipmentandtrackingsystem.repository.EquipmentRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.MaintenanceRepository;
import com.wecp.medicalequipmentandtrackingsystem.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private  UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);

    public MaintenanceService(MaintenanceRepository maintenanceRepository){
        this.maintenanceRepository = maintenanceRepository;
    }

    // finds all the maintenances
    public List<Maintenance> getAllMaintenances(){
        logger.info("Fetching all maintenance records");
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        logger.info("Fetched {} maintenance records", maintenances.size());
        return maintenances;
    }

    // update maintenance
    public Maintenance updateMaintenance(long maintenanceId, Maintenance updatedMaintenance) {
        logger.info("Attempting to update maintenance with ID: {}", maintenanceId);
        Maintenance existingMaintenance = maintenanceRepository.findById(maintenanceId)
            .orElseThrow(() -> {
                logger.error("Maintenance not found with ID: {}", maintenanceId);
                return new ResourceNotFoundException("Maintenance not found!");
            });

        logger.info("Maintenance found. Updating details for ID: {}", maintenanceId);
        existingMaintenance.setDescription(updatedMaintenance.getDescription());
        existingMaintenance.setCompletedDate(updatedMaintenance.getCompletedDate());
        existingMaintenance.setStatus(updatedMaintenance.getStatus());

        Maintenance savedMaintenance = maintenanceRepository.save(existingMaintenance);
        logger.info("Maintenance updated successfully for ID: {}", maintenanceId);
        return savedMaintenance;
    }
    
    // create maintenance
    public Maintenance createMaintenance(Long equipmentId, Maintenance maintenance) {
        logger.info("Creating maintenance for equipment ID: {}", equipmentId);
        maintenance.setEquipment(equipmentRepository.findById(equipmentId)
            .orElseThrow(() -> {
                logger.error("Equipment not found with ID: {}", equipmentId);
                return new ResourceNotFoundException("Equipment not found with ID: " + equipmentId);
            }));

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        logger.info("Maintenance created successfully with ID: {}", savedMaintenance.getId());
        return savedMaintenance;
    }

    // find maintenance by ID
    public Maintenance findById(Long maintenanceId) {
        logger.info("Fetching maintenance with ID: {}", maintenanceId);
        return maintenanceRepository.findById(maintenanceId)
            .orElseThrow(() -> {
                logger.error("Maintenance not found with ID: {}", maintenanceId);
                return new ResourceNotFoundException("Maintenance not found with ID: " + maintenanceId);
            });
    }
    public Integer getAllTechnicians()
    {
        Integer count=0;
        List<User> users = userRepository.findAll();
            for (User user : users) {
                if("TECHNICIAN".equals(user.getRole()))
                {
                    count=count+1;
                }
            }
            return count;
            
    }
}
