package com.example.Vaccination_booking_system.service;

import com.example.Vaccination_booking_system.dto.requestDto.AddVaccinationCenterRequestDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddAppointmentResponseDto;
import com.example.Vaccination_booking_system.dto.responseDto.AddVaccinationCenterResponseDto;
import com.example.Vaccination_booking_system.exception.DoctorNotFound;
import com.example.Vaccination_booking_system.exception.PersonNotFound;
import com.example.Vaccination_booking_system.model.Appointment;
import com.example.Vaccination_booking_system.model.Doctor;
import com.example.Vaccination_booking_system.model.Person;
import com.example.Vaccination_booking_system.model.VaccinationCenter;
import com.example.Vaccination_booking_system.repository.AppointmentRepository;
import com.example.Vaccination_booking_system.repository.DoctorRepository;
import com.example.Vaccination_booking_system.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    JavaMailSender javaMailSender;
    public AddAppointmentResponseDto addApppointment(int personId, int doctorId) {
        Optional<Person> optionalPerson=personRepository.findById(personId);
        Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
        if(optionalPerson.isEmpty()){
            throw new PersonNotFound("Person with Id "+ personId +" not found");
        }
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFound("Doctor with Id "+ doctorId+" not found");
        }
        Appointment appointment=new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(optionalPerson.get());
        appointment.setDoctor(optionalDoctor.get());

        Appointment savedAppointment=appointmentRepository.save(appointment);

        Doctor doctor=optionalDoctor.get();
        Person person=optionalPerson.get();

        doctor.getAppointment().add(savedAppointment);
        person.getAppointment().add(savedAppointment);

        personRepository.save(person);
        doctorRepository.save(doctor);

        VaccinationCenter savedVaccinationCenter = doctor.getVaccinationCenter();
        // Send mail

        String emailBody="Congratulatins ! Mr. "+ person.getName()+ " your appointment for vaccine is booked with "+doctor.getName()+
        " please reach your vaccination center which is at "+savedVaccinationCenter.getName()+ " "+savedVaccinationCenter.getAddress()+
                " Dhanyawad.";

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("t12627216@gmail.com");
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("Your vaccine appointment is scheduled");
        simpleMailMessage.setText(emailBody);

       javaMailSender.send((simpleMailMessage));


        AddVaccinationCenterResponseDto addVaccinationCenterResponseDto= new AddVaccinationCenterResponseDto();
        addVaccinationCenterResponseDto.setName(savedVaccinationCenter.getName());
        addVaccinationCenterResponseDto.setAdd(savedVaccinationCenter.getAddress());
        addVaccinationCenterResponseDto.setCenterType(savedVaccinationCenter.getCenterType());
        addVaccinationCenterResponseDto.setCenterId(savedVaccinationCenter.getCenterId());


        AddAppointmentResponseDto addAppointmentResponseDto=new AddAppointmentResponseDto();

        addAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        addAppointmentResponseDto.setPersonName(person.getName());
        addAppointmentResponseDto.setDoctorName(doctor.getName());
        addAppointmentResponseDto.setAddVaccinationCenterResponseDto(addVaccinationCenterResponseDto);

        return addAppointmentResponseDto;

    }
}
