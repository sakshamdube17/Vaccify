package com.example.Vaccination_booking_system.repository;

import com.example.Vaccination_booking_system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByEmail(String email);
}
