package com.example.Vaccination_booking_system.controller;

import com.example.Vaccination_booking_system.dto.requestDto.AddPersonRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddPersonResponseDto;
import com.example.Vaccination_booking_system.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto) {
        try {
            AddPersonResponseDto addPersonResponseDto = personService.add(addPersonRequestDto);
            return new ResponseEntity(addPersonResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Wrong entry", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        try {
           personService.updateEmail(oldEmail,newEmail);
           return new ResponseEntity<>("Email updated successfully",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
  }

