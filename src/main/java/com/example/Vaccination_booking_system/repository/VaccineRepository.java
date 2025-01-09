package com.example.Vaccination_booking_system.repository;

import com.example.Vaccination_booking_system.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
}
