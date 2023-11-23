package com.gdev.geekacademybackend.services.dto;

import com.gdev.geekacademybackend.models.File;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String name;
    private String url;
    private String type;
    private long size;

    public FileResponse(File file) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                .path(file.getId()).toUriString();

        this.url = fileDownloadUri;
        this.name = file.getName();
        this.type = file.getType();
        this.size = file.getData().length;
    }

}
