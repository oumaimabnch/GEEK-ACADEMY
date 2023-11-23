package com.gdev.geekacademybackend.services.dto;

import com.gdev.geekacademybackend.models.File;
import com.gdev.geekacademybackend.services.payload.UserSummary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;
    private UserSummary author;
    private String title;
    private String textBody;
    private Long courseId;
    private File jointFile;
}
