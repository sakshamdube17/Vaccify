package com.example.Vaccination_booking_system.dto.responseDto;

import com.example.Vaccination_booking_system.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddVaccinationCenterResponseDto {

   String name;
   String centerId;

   String add;

   CenterType centerType;

}
