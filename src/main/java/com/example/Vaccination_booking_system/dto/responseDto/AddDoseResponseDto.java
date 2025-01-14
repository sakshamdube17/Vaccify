package com.example.Vaccination_booking_system.dto.responseDto;

import com.example.Vaccination_booking_system.Enum.VaccineType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDoseResponseDto {
    String personName;
    VaccineType vaccineType;
    String message;
}
