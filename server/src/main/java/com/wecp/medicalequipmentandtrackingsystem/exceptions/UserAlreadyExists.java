package com.wecp.medicalequipmentandtrackingsystem.exceptions;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String message){
        super(message);
    }

}
