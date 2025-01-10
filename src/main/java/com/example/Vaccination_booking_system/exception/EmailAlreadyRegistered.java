package com.example.Vaccination_booking_system.exception;

public class EmailAlreadyRegistered extends RuntimeException{
    public EmailAlreadyRegistered(String mssg){
        super(mssg);
    }
}
