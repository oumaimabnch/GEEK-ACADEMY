package com.gdev.geekacademybackend.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDto extends UserDto {

    private Long ncin;

    private String grade;

}
