package com.wecp.medicalequipmentandtrackingsystem.entitiy;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders") // Defines the table name 
public class Order {

    //Declares it as a primary key in the id
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Date orderDate;

    //Pending , Shipped , Delivered
    String status = "Pending";  
    
    int quantity;
    
    //Unidirectional 
    @ManyToOne
    Equipment equipment;

    //No-arg constructor
    public Order(){}

    //Parameterised Constructor 1
    public Order(Date orderDate, String status, int quantity, Equipment equipment) {
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.equipment = equipment;
    }

    //Parameterised Constructor 2
    public Order(Date orderDate, String status, int quantity) {
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
    }

    //getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    
    

}
