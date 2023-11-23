package com.gdev.geekacademybackend.services.dto;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    private Long id;

    private LocalDate happenAt;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time startingTime;

    private Time endingTime;

    private DayOfWeek dayOfWeek;

}
