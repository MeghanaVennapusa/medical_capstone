package com.wecp.medicalequipmentandtrackingsystem.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital") // do not change table name
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    //@Column(name="hospital_name")
    private String name;

    //@Column(name="location")
    private String location;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    //@Column(name = "equipment_list")
    private List<Equipment> equipmentList = new ArrayList<>();

    public Hospital() {
    }

    public Hospital(String name, String location, List<Equipment> equipmentList) {
        
        this.name = name;
        this.location = location;
        this.equipmentList = equipmentList;
    }

    public Hospital(String name, String location){
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }


    


   
}