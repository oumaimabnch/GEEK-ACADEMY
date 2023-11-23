package com.gdev.geekacademybackend.services.dto;

import com.gdev.geekacademybackend.services.payload.UserSummary;

import lombok.Data;

@Data
public class PostResponse {

    private long id;
    private UserSummary author;
    private String title;
    private String textBody;
    private Long courseId;
    private FileResponse jointFile;
}
