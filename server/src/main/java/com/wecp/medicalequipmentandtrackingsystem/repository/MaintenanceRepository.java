package com.wecp.medicalequipmentandtrackingsystem.repository;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// extending jpa repository
@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

}