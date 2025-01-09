package com.example.Vaccination_booking_system.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDoseResponseDto {
    String personName;
    String message;
}
