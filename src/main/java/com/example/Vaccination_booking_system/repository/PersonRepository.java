package com.example.Vaccination_booking_system.repository;

import com.example.Vaccination_booking_system.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
