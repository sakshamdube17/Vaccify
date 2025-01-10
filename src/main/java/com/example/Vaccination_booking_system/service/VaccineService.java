package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.Enum.VaccineType;
import com.example.Vaccination_booking_system.dto.responseDto.AddDoseResponseDto;
import com.example.Vaccination_booking_system.exception.Dose1AlreadyTaken;
import com.example.Vaccination_booking_system.exception.Dose1NotTakken;
import com.example.Vaccination_booking_system.exception.Dose2AlreadyTakken;
import com.example.Vaccination_booking_system.exception.PersonNotFound;
import com.example.Vaccination_booking_system.model.Person;
import com.example.Vaccination_booking_system.model.Vaccine;
import com.example.Vaccination_booking_system.repository.PersonRepository;
import com.example.Vaccination_booking_system.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        if (person.getVaccine() == null) {
            person.setVaccine(new ArrayList<>()); // Initialize the list if it's null
        }
        person.getVaccine().add(vaccine1);
        person.setDose1Taken(true);
        Person savedPerson=personRepository.save(person);

//        vaccineRepository.save(vaccine1);

        AddDoseResponseDto addDoseResponseDto=new AddDoseResponseDto();
        addDoseResponseDto.setPersonName(savedPerson.getName());
        addDoseResponseDto.setVaccineType(person.getVaccine().get(0).getVaccineType());
        addDoseResponseDto.setMessage("Congrats!! Dose 1 taken");
        return addDoseResponseDto;
    }

    public AddDoseResponseDto addSecondDose(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()){
            throw new PersonNotFound("Person id is invalid");
        }
        Person person = optionalPerson.get();
        if(!person.isDose1Taken()){
            throw new Dose1NotTakken("Please take dose 1 first");
        }
        if(person.isDose2Taken()){
            throw new Dose2AlreadyTakken("Dose 2 already taken");
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineId(String.valueOf(UUID.randomUUID()));
        List<Vaccine> vaccineList=person.getVaccine();
        vaccine.setVaccineType(vaccineList.get(0).getVaccineType());
        vaccine.setPerson(person);

        person.getVaccine().add(vaccine);
        person.setDose2Taken(true);
        personRepository.save(person);

        AddDoseResponseDto addDoseResponseDto=new AddDoseResponseDto();
        addDoseResponseDto.setPersonName(person.getName());
        addDoseResponseDto.setVaccineType(person.getVaccine().get(1).getVaccineType());
        addDoseResponseDto.setMessage("Congrats !!Dose 2 taken");
        return addDoseResponseDto;
    }
}
