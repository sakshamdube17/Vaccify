package com.example.Vaccination_booking_system.dto.requestDto;

import com.example.Vaccination_booking_system.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPersonRequestDto {
    String name;
    int age;
    String email;
    Gender gender;
}
