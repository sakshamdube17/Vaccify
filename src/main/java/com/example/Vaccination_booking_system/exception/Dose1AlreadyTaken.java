package com.example.Vaccination_booking_system.exception;

public class Dose1AlreadyTaken extends  RuntimeException{
    public Dose1AlreadyTaken(String mssg){
        super(mssg);
    }
}
