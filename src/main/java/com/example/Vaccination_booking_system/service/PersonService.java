package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.dto.requestDto.AddPersonRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddPersonResponseDto;
import com.example.Vaccination_booking_system.exception.PersonNotFound;
import com.example.Vaccination_booking_system.model.Person;
import com.example.Vaccination_booking_system.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    public AddPersonResponseDto add(AddPersonRequestDto addPersonRequestDto) {
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setEmail(addPersonRequestDto.getEmail());
        person.setAge(addPersonRequestDto.getAge());
        person.setGender(addPersonRequestDto.getGender());

        personRepository.save(person);

        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(person.getName());
        addPersonResponseDto.setMessage("added susseccfully");
        return  addPersonResponseDto;
    }

    public void updateEmail(String oldEmail,String newEmail) {
        Person person = personRepository.findByEmail(oldEmail);
        if(person==null){
            throw new PersonNotFound("Person with "+ oldEmail +" not found");
        }

        person.setEmail(newEmail);
        personRepository.save(person);
    }
}
