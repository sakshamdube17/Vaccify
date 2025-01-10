package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.dto.requestDto.AddDoctorRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddDoctorResponseDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddDoseResponseDto;
import com.example.Vaccination_booking_system.exception.EmailAlreadyRegistered;
import com.example.Vaccination_booking_system.model.Doctor;
import com.example.Vaccination_booking_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public AddDoctorResponseDto addDoctor(AddDoctorRequestDto addDoctorRequestDto) {
       Doctor doctorAlreadyPresent=doctorRepository.findByEmail(addDoctorRequestDto.getEmail());
       if(doctorAlreadyPresent!=null){
           throw new EmailAlreadyRegistered("Email already registered");
       }

       Doctor doctor=new Doctor();
       doctor.setName(addDoctorRequestDto.getName());
       doctor.setEmail(addDoctorRequestDto.getEmail());
       doctor.setGender(addDoctorRequestDto.getGender());

       doctorRepository.save(doctor);

        AddDoctorResponseDto addDoctorResponseDto=new AddDoctorResponseDto();
        addDoctorResponseDto.setName(doctor.getName());
        addDoctorResponseDto.setEmail(doctor.getEmail());
        addDoctorResponseDto.setMssg("Doctor registered successfully");

        return addDoctorResponseDto;
    }
}
