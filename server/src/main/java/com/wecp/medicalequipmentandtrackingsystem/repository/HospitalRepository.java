package com.wecp.medicalequipmentandtrackingsystem.repository;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // extends JpaRepositor and add custom methods if needed
    Hospital findByName(String name);

    Hospital findByLocation(String location);

    Hospital findByNameAndLocation(String name, String location);

}
