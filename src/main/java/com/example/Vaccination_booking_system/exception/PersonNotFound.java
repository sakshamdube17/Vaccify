package com.example.Vaccination_booking_system.exception;

public class PersonNotFound extends RuntimeException{
    public PersonNotFound(String mssg){
        super(mssg);
    }
}
