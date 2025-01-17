package com.example.Vaccination_booking_system.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddAppointmentResponseDto {
    String appointmentId;
    String personName;
    String doctorName;
    AddVaccinationCenterResponseDto addVaccinationCenterResponseDto;
}
