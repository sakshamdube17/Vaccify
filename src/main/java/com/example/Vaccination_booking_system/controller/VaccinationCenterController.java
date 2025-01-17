package com.example.Vaccination_booking_system.controller;

import com.example.Vaccination_booking_system.dto.requestDto.AddVaccinationCenterRequestDto;
import com.example.Vaccination_booking_system.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add_center")
    public ResponseEntity addVaccinationCenter(@RequestBody AddVaccinationCenterRequestDto addVaccinationCenterRequestDto){
        try{
            return new ResponseEntity(vaccinationCenterService.addVaccinationCenter(addVaccinationCenterRequestDto),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
