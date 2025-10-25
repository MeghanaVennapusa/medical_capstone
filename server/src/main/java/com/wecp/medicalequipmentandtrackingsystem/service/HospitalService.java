package com.wecp.medicalequipmentandtrackingsystem.service;


import java.util.List;

import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospitals() throws Exception{
        return hospitalRepository.findAll();
    }

    public Hospital addHospital(Hospital hospital) throws Exception{
        Hospital existingHospital=hospitalRepository.findByNameAndLocation(hospital.getName(), hospital.getLocation());
        if(existingHospital != null){
            throw new RuntimeException("Hospital already with same name and location");
        }
        return hospitalRepository.save(hospital);
    }

    public Hospital getHospitalById(Long id) throws Exception{
        return hospitalRepository.findById(id).get();
    }
    
}