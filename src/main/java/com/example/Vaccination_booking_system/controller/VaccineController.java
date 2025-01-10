package com.example.Vaccination_booking_system.controller;

import com.example.Vaccination_booking_system.Enum.VaccineType;
import com.example.Vaccination_booking_system.model.Vaccine;
import com.example.Vaccination_booking_system.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    @Autowired
    VaccineService vaccineService;
    @PostMapping("/first_dose")
    public ResponseEntity firstDose(@RequestParam("id") int personId, @RequestParam("vaccineType") VaccineType vaccine){
        try{

            return new  ResponseEntity(vaccineService.addFirstDose(personId,vaccine), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/second_dose")
    public ResponseEntity secondDose(@RequestParam int id){
        try{
            return new ResponseEntity(vaccineService.addSecondDose(id),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
