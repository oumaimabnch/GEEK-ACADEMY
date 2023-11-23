package com.gdev.geekacademybackend.services.payload;

import lombok.Data;

@Data
public class UserSummary {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;

}
