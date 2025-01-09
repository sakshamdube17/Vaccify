package com.example.Vaccination_booking_system.model;

import com.example.Vaccination_booking_system.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vaccine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    String vaccineId;

    @Enumerated(EnumType.STRING)
    VaccineType vaccineType;

    @CreationTimestamp
    Date vaccinationDate;

    @ManyToOne
    @JoinColumn
    Person person;
}
