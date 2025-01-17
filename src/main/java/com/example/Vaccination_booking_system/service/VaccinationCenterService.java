package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.dto.requestDto.AddVaccinationCenterRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddVaccinationCenterResponseDto;
import com.example.Vaccination_booking_system.model.Doctor;
import com.example.Vaccination_booking_system.model.VaccinationCenter;
import com.example.Vaccination_booking_system.repository.DoctorRepository;
import com.example.Vaccination_booking_system.repository.VaccinationCenterRepository;
import com.example.Vaccination_booking_system.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    @Autowired
    DoctorRepository doctorRepository;
    public AddVaccinationCenterResponseDto addVaccinationCenter(AddVaccinationCenterRequestDto addVaccinationCenterRequestDto) {
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        vaccinationCenter.setName(addVaccinationCenterRequestDto.getName());
        vaccinationCenter.setCenterId(String.valueOf(UUID.randomUUID()));
        vaccinationCenter.setAddress(addVaccinationCenterRequestDto.getAdd());
        vaccinationCenter.setCenterType(addVaccinationCenterRequestDto.getCenterType());
        List<Doctor>doctorList=new ArrayList<>();
        for(int x:addVaccinationCenterRequestDto.getDoctorId()){
            Optional<Doctor> optionalDoctor=doctorRepository.findById(x);
            if(optionalDoctor.isPresent()){
                Doctor doctor=optionalDoctor.get();
                doctor.setVaccinationCenter(vaccinationCenter);
                doctorList.add(doctor);
            }
        }
        vaccinationCenter.setDoctor(doctorList);

        VaccinationCenter savedVaccinationCenter=vaccinationCenterRepository.save(vaccinationCenter);

        AddVaccinationCenterResponseDto addVaccinationCenterResponseDto=new AddVaccinationCenterResponseDto();

        addVaccinationCenterResponseDto.setName(savedVaccinationCenter.getName());
        addVaccinationCenterResponseDto.setAdd(savedVaccinationCenter.getAddress());
        addVaccinationCenterResponseDto.setCenterType(savedVaccinationCenter.getCenterType());
        addVaccinationCenterResponseDto.setCenterId(savedVaccinationCenter.getCenterId());

        return addVaccinationCenterResponseDto;
    }
}
