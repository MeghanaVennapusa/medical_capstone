package com.wecp.medicalequipmentandtrackingsystem.controller;


import com.wecp.medicalequipmentandtrackingsystem.entitiy.Equipment;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Hospital;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Maintenance;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.Order;
import com.wecp.medicalequipmentandtrackingsystem.service.EquipmentService;
import com.wecp.medicalequipmentandtrackingsystem.service.HospitalService;
import com.wecp.medicalequipmentandtrackingsystem.service.MaintenanceService;
import com.wecp.medicalequipmentandtrackingsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class HospitalController {
     @Autowired
     EquipmentService equipmentService;
     
     // create hospital and return the created hospital with status code 201 = CREATED;
    @PostMapping("/api/hospital/create")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
      
    }
    
     // return all hospitals with response code = 200 ok
    @GetMapping("/api/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
       
    }

     // add equipment to the hospital and return the added equipment with status code 201 = CREATED;
    @PostMapping("/api/hospital/equipment")
    public ResponseEntity<Equipment> addEquipment(@RequestParam Long hospitalId, @RequestBody Equipment equipment) {
         Equipment equip = equipmentService.createEquipment(equipment);
         if(equip == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
         else{
            return new ResponseEntity<Equipment>(equip, HttpStatus.CREATED);
         }
    }

    // return all equipments of hospital with response code = 200 OK
    @GetMapping("/api/hospital/equipment/{hospitalId}")
    public ResponseEntity<List<Equipment>> getAllEquipmentsOfHospital(@PathVariable Long hospitalId) {
        List<Equipment> eqlist = equipmentService.getAllEquipment();
        if(eqlist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<List<Equipment>>(eqlist, HttpStatus.OK);
        }
    }

    // schedule maintenance for the equipment and return the scheduled maintenance with status code 201 = CREATED;
    @PostMapping("/api/hospital/maintenance/schedule")
    public ResponseEntity<Maintenance> scheduleMaintenance
            (@RequestParam Long equipmentId, @RequestBody Maintenance maintenance) {
        
    }

     // place order for the equipment and return the placed order with status code 201 = CREATED;
    @PostMapping("/api/hospital/order")
    public ResponseEntity<Order> placeOrder(@RequestParam Long equipmentId, @RequestBody Order order) {
       
    }
}

