package com.example.Vaccination_booking_system.model;

import com.example.Vaccination_booking_system.Enum.Gender;
import com.example.Vaccination_booking_system.service.PersonService;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    @Column(nullable = false, unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    boolean isDose1Taken;
    boolean isDose2Taken;


    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Vaccine> vaccine;

    @OneToOne(mappedBy="person",cascade = CascadeType.ALL)
    Certificate certificate;

    @OneToMany(mappedBy="person",cascade = CascadeType.ALL)
    List <Appointment> appointment=new ArrayList<>();
}
