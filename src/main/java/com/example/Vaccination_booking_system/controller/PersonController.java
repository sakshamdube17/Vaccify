package com.example.Vaccination_booking_system.controller;

import com.example.Vaccination_booking_system.dto.requestDto.AddPersonRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddPersonResponseDto;
import com.example.Vaccination_booking_system.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try {
            AddPersonResponseDto addPersonResponseDto=personService.add(addPersonRequestDto);
            return new ResponseEntity(addPersonResponseDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity("Wrong info",HttpStatus.BAD_REQUEST );
        }
    }
}
