package com.example.Vaccination_booking_system.exception;

public class DoctorNotFound extends RuntimeException{
    public DoctorNotFound(String mssg){
        super(mssg);
    }
}
