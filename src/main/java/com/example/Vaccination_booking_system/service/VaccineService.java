package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.Enum.VaccineType;
import com.example.Vaccination_booking_system.dto.responseDto.AddDoseResponseDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddPersonResponseDto;
import com.example.Vaccination_booking_system.exception.Dose1AlreadyTaken;
import com.example.Vaccination_booking_system.exception.PersonNotFound;
import com.example.Vaccination_booking_system.model.Person;
import com.example.Vaccination_booking_system.model.Vaccine;
import com.example.Vaccination_booking_system.repository.PersonRepository;
import com.example.Vaccination_booking_system.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VaccineService {
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    PersonRepository personRepository;
    public AddDoseResponseDto addFirstDose(int personId, VaccineType vaccine) {
       Optional <Person> optionalPerson = personRepository.findById(personId);

       if(!optionalPerson.isPresent()){
           throw new PersonNotFound("Invalid Id");
       }
       Person person =optionalPerson.get();
       if(person.isDose1Taken()){
           throw new Dose1AlreadyTaken("Dose 1 already taken");
       }

       Vaccine vaccine1 = new Vaccine();
       vaccine1.setVaccineId(String.valueOf(UUID.randomUUID()));
       vaccine1.setVaccineType(vaccine);
       vaccine1.setPerson(person);

        person.setDose1Taken(true);
        Person savedPerson=personRepository.save(person);

        AddDoseResponseDto addDoseResponseDto=new AddDoseResponseDto();
        addDoseResponseDto.setPersonName(savedPerson.getName());
        addDoseResponseDto.setMessage("Congrats!! Dose 1 taken");
        return addDoseResponseDto;
    }
}
