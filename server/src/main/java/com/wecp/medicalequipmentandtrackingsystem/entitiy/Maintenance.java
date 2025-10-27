package com.wecp.medicalequipmentandtrackingsystem.entitiy;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
@Table(name = "maintenances") // do not change table name
public class Maintenance {

    @Id //Define's the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date scheduledDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date completedDate;

    String description;
    String status;

    @ManyToOne //ManyToOne relationship 
    Equipment equipment;

    //no-arg constructor
    public Maintenance(){} 

    //parameterized constructor 1
    public Maintenance(Date scheduledDate, Date completedDate, String description, String status) {
        this.scheduledDate = scheduledDate;
        this.completedDate = completedDate;
        this.description = description;
        this.status = status;
    }

    //parameterized constructor 2
    public Maintenance(Date scheduledDate, Date completedDate , String description , String status , Equipment equipment){

        this.scheduledDate = scheduledDate;
        this.completedDate = completedDate;
        this.description = description;
        this.status = status;
        this.equipment = equipment;

    }

    //getters and setters
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    

    
}