package com.example.Vaccination_booking_system.dto.requestDto;

import com.example.Vaccination_booking_system.Enum.CenterType;
import com.example.Vaccination_booking_system.model.Doctor;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddVaccinationCenterRequestDto {

    String name;
    String add;

    CenterType centerType;

    List<Integer> doctorId;
}
